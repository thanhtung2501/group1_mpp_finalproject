package mpp.business.service;

import mpp.business.exception.AddBookException;
import mpp.business.exception.CheckoutBookException;
import mpp.business.model.*;
import mpp.business.util.Constant;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static mpp.business.util.CommonUtil.isValidIsbn;


public class BookService extends AbstractLibraryService {
    private Map<String, Book> bookMap;

    public Book addBook(String isbn, String title, int maxCheckoutLength, List<Author> authors) throws AddBookException {
        // Validate parameters
        if (!isValidIsbn(isbn)) {
            throw new AddBookException("Invalid ISBN number. Valid ISBN number must follow this format 12-12345.");
        }
        bookMap = dataAccess.readBooksMap();
        for (Book book : bookMap.values()) {
            if (book.getIsbn().equals(isbn)) {
                throw new AddBookException("A book with the ISBN " + isbn + " already existed.");
            }
        }
        if (title == null || title.isEmpty()) {
            throw new AddBookException("No title specified.");
        }
        if (maxCheckoutLength < 1) {
            throw new AddBookException("Invalid max checkout length. It must be > 0.");
        }
        if (authors.size() < 1) {
            throw new AddBookException("No author.");
        }

        // Create new book and save it
        Book book = new Book(isbn, title, maxCheckoutLength, authors);
        bookMap.put(isbn, book);
        saveBooks(bookMap);

        return book;
    }

    public BookCopy addBookCopy(String isbn) {
        // Validate parameters
        if (!isValidIsbn(isbn)) {
            throw new AddBookException("Invalid ISBN number. Valid ISBN number must follow this format 12-12345.");
        }
        bookMap = dataAccess.readBooksMap();
        Book book = bookMap.get(isbn);
        if (book == null) {
            throw new AddBookException("Book does not exist.");
        }

        // Add copy
        book.addCopy();
        saveBooks(bookMap);

        return new BookCopy(book, book.getNumCopies() + 1, true);
    }

    public Map<String, Book> findAllBooks() {
        bookMap = dataAccess.readBooksMap();
        return bookMap;
    }

    public void saveBooks(Map<String, Book> books) {
        dataAccess.saveBooksMap(books);
    }

    public LibraryMember checkout(String libraryMemberID, List<String> isbn) throws CheckoutBookException {
        //TODO
        return null;
    }

    public LibraryMember checkout(String libraryMemberID, String isbn) throws CheckoutBookException {
        Map<String, LibraryMember> libraryMemberMap = dataAccess.readMemberMap();
        LibraryMember libraryMember = Optional.ofNullable(libraryMemberMap.get(libraryMemberID))
                .orElseThrow(() -> new CheckoutBookException("No library members found with " + libraryMemberID));

        Map<String, Book> bookMap = dataAccess.readBooksMap();
        Book book = Optional.ofNullable(bookMap.get(isbn))
                .orElseThrow(() -> new CheckoutBookException("No books found with isbn " + isbn));
        BookCopy availableBookCopy = Arrays.stream(book.getCopies())
                .filter(BookCopy::isAvailable)
                .findFirst()
                .orElseThrow(() -> new CheckoutBookException("No book copies available of " + isbn));

        // Make this book copy unavailable
        availableBookCopy.changeAvailability();

        LocalDate checkoutDate = LocalDate.now();
        LocalDate dueDate = checkoutDate.plusDays(book.getMaxCheckoutLength());
        double fines = 0;
        if (LocalDate.now().isAfter(dueDate)) {
            fines = Constant.FINE_20;
        }

        CheckoutRecordEntry checkoutRecordEntry = new CheckoutRecordEntry(
                isbn,
                String.valueOf(availableBookCopy.getCopyNum()),
                checkoutDate,
                dueDate,
                fines,
                LocalDate.now().plusDays(book.getMaxCheckoutLength()).plusDays(Constant.FINE_PAID_INCREMENTAL));

        libraryMember.getCheckoutRecord().addCheckoutRecordEntry(checkoutRecordEntry);

        // Persist books and their book copies
        dataAccess.saveBooksMap(bookMap);

        // Persist checkout record entry to its library member
        dataAccess.saveNewMember(libraryMember);

        return dataAccess.readMemberMap().get(libraryMemberID);
    }
}

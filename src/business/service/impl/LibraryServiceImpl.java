package business.service.impl;

import business.model.Author;
import business.model.Book;
import business.model.LibraryMember;
import business.service.AbstractLibraryService;
import business.service.BookService;
import business.service.LibraryMemberService;
import business.service.LibraryService;

import java.util.List;

public class LibraryServiceImpl extends AbstractLibraryService implements LibraryService {


    protected BookService bookService = new BookService();

    protected LibraryMemberService libraryMemberService = new LibraryMemberService();

    @Override
    public LibraryMember checkoutBook(String libraryMemberID, String isbn) {
        return bookService.checkout(libraryMemberID, isbn);
    }

    @Override
    public LibraryMember checkoutBooks(String libraryMemberID, List<String> listIsbn) {
        return bookService.checkout(libraryMemberID, listIsbn);
    }

    @Override
    public Book addBook(String isbn, String title, int maxCheckoutLength, List<Author> authors, int numberOfCopies) {
        return bookService.addBook(isbn, title, maxCheckoutLength, authors, numberOfCopies);
    }

    @Override
    public void addUpdateNewLibraryMember(LibraryMember libraryMember) {
        libraryMemberService.addUpdateNewLibraryMember(libraryMember);
    }

    @Override
    public Book addBookCopy(String isbn) {
        return bookService.addBookCopy(isbn);
    }
}

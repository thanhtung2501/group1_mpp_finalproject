package mpp.business.service.impl;

import mpp.business.model.Author;
import mpp.business.model.Book;
import mpp.business.model.BookCopy;
import mpp.business.model.LibraryMember;
import mpp.business.service.AbstractLibraryService;
import mpp.business.service.BookService;
import mpp.business.service.LibraryMemberService;
import mpp.business.service.LibraryService;

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
    public Book addBook(String isbn, String title, int maxCheckoutLength, List<Author> authors) {
        return bookService.addBook(isbn, title, maxCheckoutLength, authors);
    }

    @Override
    public void addUpdateLibraryMember(LibraryMember libraryMember) {
        libraryMemberService.addUpdateLibraryMember(libraryMember);
    }

    @Override
    public BookCopy addBookCopy(String isbn) {
        return bookService.addBookCopy(isbn);
    }
}

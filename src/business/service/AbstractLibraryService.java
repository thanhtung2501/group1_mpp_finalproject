package business.service;


import business.model.Author;
import business.model.Book;
import business.model.LibraryMember;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

import java.util.List;

public class AbstractLibraryService implements LibraryService {
    protected DataAccess dataAccess = new DataAccessFacade();

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
};
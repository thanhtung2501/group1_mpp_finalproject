package business.service;


import business.model.Author;
import business.model.Book;
import business.model.LibraryMember;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

import java.util.List;

public class AbstractLibraryService implements LibraryService {
    protected DataAccess dataAccess = new DataAccessFacade();

    protected BookLibraryService bookService = new BookLibraryService();

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
};

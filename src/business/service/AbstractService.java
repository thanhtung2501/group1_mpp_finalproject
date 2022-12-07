package business.service;


import business.model.CheckoutRecord;
import business.model.LibraryMember;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

import java.util.List;

public class AbstractService implements Service {
    protected DataAccess dataAccess = new DataAccessFacade();

    protected BookService bookService = new BookService();

    @Override
    public LibraryMember checkoutBook(String libraryMemberID, String isbn) {
        return bookService.checkout(libraryMemberID, isbn);
    }

    @Override
    public LibraryMember checkoutBooks(String libraryMemberID, List<String> listIsbn) {
        return bookService.checkout(libraryMemberID, listIsbn);
    }
};

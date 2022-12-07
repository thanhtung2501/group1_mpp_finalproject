package business.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import business.model.*;
import business.exception.LoginException;
import business.service.LibraryService;
import business.service.LoginService;
import business.service.impl.LibraryServiceImpl;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public class SystemController implements ControllerInterface {
    private LoginService loginService = new LoginService();

    private LibraryService libraryService = new LibraryServiceImpl();

    public Role login(String username, String password) throws LoginException {
        return loginService.login(username, password);
    }

    @Override
    public List<String> allMemberIds() {
        DataAccess da = new DataAccessFacade();
        List<String> retval = new ArrayList<>();
        retval.addAll(da.readMemberMap().keySet());
        return retval;
    }

    @Override
    public List<String> allBookIds() {
        DataAccess da = new DataAccessFacade();
        List<String> retval = new ArrayList<>();
        retval.addAll(da.readBooksMap().keySet());
        return retval;
    }

    @Override
    public LibraryMember checkoutBook(String libraryMemberID, String isbn) {
//        return libraryService.checkoutBook(libraryMemberID, isbn);
        return null;
    }

    @Override
    public LibraryMember checkoutBooks(String libraryMemberID, List<String> listIsbn) {
//        return libraryService.checkoutBooks(libraryMemberID, listIsbn);
        return null;
    }

    @Override
    public Book addBook(String isbn, String title, int maxCheckoutLength, List<Author> authors) {
//        return libraryService.addBook(isbn, title, maxCheckoutLength, authors);
        return null;
    }

    @Override
    public void addUpdateNewLibraryMember(LibraryMember libraryMember) {
//        libraryService.addUpdateNewLibraryMember(libraryMember);
    }

    @Override
    public Book addBookCopy(String isbn) {
//        return libraryService.addBookCopy(isbn);
        return null;
    }
}

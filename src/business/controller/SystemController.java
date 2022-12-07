package business.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import business.model.*;
import business.exception.LoginException;
import business.service.LibraryService;
import business.service.impl.LibraryServiceImpl;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public class SystemController implements ControllerInterface {
    public static Role currentRole = null;

    private LibraryService libraryService = new LibraryServiceImpl();

    public void login(String id, String password) throws LoginException {
        DataAccess da = new DataAccessFacade();
        HashMap<String, User> map = da.readUserMap();
        if (!map.containsKey(id)) {
            throw new LoginException("ID " + id + " not found");
        }
        String passwordFound = map.get(id).getPassword();
        if (!passwordFound.equals(password)) {
            throw new LoginException("Password incorrect");
        }
        currentRole = map.get(id).getRole();

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
        return libraryService.checkoutBook(libraryMemberID, isbn);
    }

    @Override
    public LibraryMember checkoutBooks(String libraryMemberID, List<String> listIsbn) {
        return libraryService.checkoutBooks(libraryMemberID, listIsbn);
    }

    @Override
    public Book addBook(String isbn, String title, int maxCheckoutLength, List<Author> authors) {
        return libraryService.addBook(isbn, title, maxCheckoutLength, authors);
    }
}

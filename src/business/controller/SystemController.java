package business.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import business.model.*;
import business.exception.LoginException;
import business.service.AuthorService;
import business.service.BookService;
import business.service.LibraryMemberService;
import business.service.LibraryService;
import business.service.LoginService;
import business.service.impl.LibraryServiceImpl;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public class SystemController implements ControllerInterface {
    private final LoginService loginService = new LoginService();
    private final BookService bookService = new BookService();
    private final LibraryMemberService libraryMemberService = new LibraryMemberService();
    private final AuthorService authorService = new AuthorService();

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
        return libraryService.checkoutBook(libraryMemberID, isbn);
    }

    @Override
    public LibraryMember checkoutBooks(String libraryMemberID, List<String> listIsbn) {
        return libraryService.checkoutBooks(libraryMemberID, listIsbn);
    }

    @Override
    public Book addBook(String isbn, String title, int maxCheckoutLength, List<Author> authors, int numberOfCopies) {
        return libraryService.addBook(isbn, title, maxCheckoutLength, authors, numberOfCopies);
    }

    @Override
    public void addUpdateNewLibraryMember(LibraryMember libraryMember) {
        libraryService.addUpdateNewLibraryMember(libraryMember);
    }

    @Override
    public Book addBookCopy(String isbn) {
//        return libraryService.addBookCopy(isbn);
        return null;
    }

    @Override
    public Author addAuthor(String firstName, String lastName, Address address, String phoneNumber, boolean credential, String bio) {
        return authorService.addAuthor(firstName, lastName, address, phoneNumber, credential, bio);
    }
}

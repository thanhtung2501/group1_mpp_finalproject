package mpp.business.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mpp.business.model.*;
import mpp.business.exception.LoginException;
import mpp.business.service.AuthorService;
import mpp.business.service.BookService;
import mpp.business.service.LibraryMemberService;
import mpp.business.service.LibraryService;
import mpp.business.service.LoginService;
import mpp.business.service.impl.LibraryServiceImpl;
import mpp.dataaccess.DataAccess;
import mpp.dataaccess.DataAccessFacade;

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
    public Map<String, Book> getAllBooks() {
        return bookService.findAllBooks();
    }

    @Override
    public Map<String, LibraryMember> getAllLibraryMembers() {
        return libraryMemberService.findAllLibraryMembers();
    }

    @Override
    public void deleteLibraryMember(String memberId) {
        libraryMemberService.deleteLibraryMember(memberId);
    }

    @Override
    public List<Author> getAuthors() {
        return authorService.findAllAuthors();
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

    @Override
    public void addUpdateLibraryMember(LibraryMember libraryMember) {
        libraryService.addUpdateLibraryMember(libraryMember);
    }

    @Override
    public BookCopy addBookCopy(String isbn) {
        return libraryService.addBookCopy(isbn);
    }

    @Override
    public Author addAuthor(String firstName, String lastName, Address address, String phoneNumber, boolean credential, String bio) {
        return authorService.addAuthor(firstName, lastName, address, phoneNumber, credential, bio);
    }
}

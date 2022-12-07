package business.service;

import business.model.*;

import java.util.List;

/**
 * This is a main service for Library system which responsible for all main behaviour of Library System
 */
public interface LibraryService {
    default Boolean validateRole(Role role) {
        return false;
    }

    /***
     * Checkout book and return a checkout record which already checked out.
     * @param libraryMemberID
     * @param {@link String}
     * @return {@link CheckoutRecord} a checkout record
     */
    LibraryMember checkoutBook(String libraryMemberID, String isbn);

    /***
     * Checkout book and return a checkout record which already checked out.
     * @param libraryMemberID
     * @param listIsbn {@link List}
     * @return {@CheckoutRecord} a checkout record
     */
    LibraryMember checkoutBooks(String libraryMemberID, List<String> listIsbn);

    /***
     * Add book to library System
     * @param isbn
     * @param title
     * @param maxCheckoutLength
     * @param authors
     * @return
     */
    Book addBook(String isbn, String title, int maxCheckoutLength, List<Author> authors, int numberOfCopies);

    /***
     * Add new member or update existing library member.
     * @param libraryMember
     */
    void addUpdateNewLibraryMember(LibraryMember libraryMember);

    /**
     * Add a copy of an existing book to the library collection.
     * @param isbn
     * @return {@link Book}
     */
    Book addBookCopy(String isbn);
}

package business.service;

import business.model.CheckoutRecord;
import business.model.LibraryMember;
import business.model.Role;

import java.util.List;

/**
 * This is a main service for Library system which responsible for all main behaviour of Library System
 */
public interface Service {
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
}

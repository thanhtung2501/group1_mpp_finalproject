package business.controller;

import business.exception.LoginException;
import business.model.Address;
import business.model.Author;
import business.model.Book;
import business.model.LibraryMember;
import business.model.Role;

import java.util.List;

public interface ControllerInterface {
	Role login(String id, String password) throws LoginException;
	List<String> allMemberIds();
	List<String> allBookIds();

	/***
	 * Checkout book and return a checkout record which already checked out.
	 * @param libraryMemberID
	 * @param {@link String}
	 * @return {@link LibraryMember} a checkout record
	 */
	LibraryMember checkoutBook(String libraryMemberID, String isbn);

	/***
	 * Checkout book and return a checkout record which already checked out.
	 * @param libraryMemberID
	 * @param listIsbn {@link List}
	 * @return {@LibraryMember} a checkout record
	 */
	LibraryMember checkoutBooks(String libraryMemberID, List<String> listIsbn);

	Book addBook(String isbn, String title, int maxCheckoutLength, List<Author> authors, int numberOfCopies);

	void addUpdateNewLibraryMember(LibraryMember libraryMember);

	Book addBookCopy(String isbn);

	Author addAuthor(String firstName, String lastName, Address address, String phoneNumber, boolean credentials, String bio);
	
}

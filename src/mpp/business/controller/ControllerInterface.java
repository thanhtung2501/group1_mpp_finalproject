package mpp.business.controller;

import mpp.business.exception.LoginException;
import mpp.business.model.Address;
import mpp.business.model.Author;
import mpp.business.model.Book;
import mpp.business.model.LibraryMember;
import mpp.business.model.Role;

import java.util.List;
import java.util.Map;

public interface ControllerInterface {

	Map<String, Book> getAllBooks();

	Map<String, LibraryMember> getAllLibraryMembers();

	Role login(String id, String password) throws LoginException;
	List<String> allMemberIds();
	List<String> allBookIds();

	List<Author> getAuthors();

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

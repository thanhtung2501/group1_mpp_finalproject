package business.controller;

import business.exception.LoginException;
import business.model.CheckoutRecord;

import java.util.List;

public interface ControllerInterface {
	void login(String id, String password) throws LoginException;
	List<String> allMemberIds();
	List<String> allBookIds();

	/***
	 * Checkout book and return a checkout record which already checked out.
	 * @param libraryMemberID
	 * @param {@link String}
	 * @return {@link CheckoutRecord} a checkout record
	 */
	CheckoutRecord checkoutBook(String libraryMemberID, String isbn);

	/***
	 * Checkout book and return a checkout record which already checked out.
	 * @param libraryMemberID
	 * @param listIsbn {@link List}
	 * @return {@CheckoutRecord} a checkout record
	 */
	CheckoutRecord checkoutBook(String libraryMemberID, List<String> listIsbn);
	
}

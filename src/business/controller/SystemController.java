package business.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import business.model.CheckoutRecord;
import business.model.Role;
import business.exception.LoginException;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import business.model.User;

public class SystemController implements ControllerInterface {
	public static Role currentRole = null;
	
	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if(!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if(!passwordFound.equals(password)) {
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
	public CheckoutRecord checkoutBook(String libraryMemberID, String isbn) {
		//TODO
		return null;
	}

	@Override
	public CheckoutRecord checkoutBook(String libraryMemberID, List<String> listIsbn) {
		//TODO
		return null;
	}
}

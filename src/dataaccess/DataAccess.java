package dataaccess;

import java.util.HashMap;

import business.model.Book;
import business.model.LibraryMember;
import business.model.User;

public interface DataAccess { 
	HashMap<String,Book> readBooksMap();
	HashMap<String, User> readUserMap();
	HashMap<String, LibraryMember> readMemberMap();
	void saveNewMember(LibraryMember member);
}

package mpp.dataaccess;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mpp.business.model.Author;
import mpp.business.model.Book;
import mpp.business.model.LibraryMember;
import mpp.business.model.User;

public interface DataAccess { 
	HashMap<String,Book> readBooksMap();
	HashMap<String, User> readUserMap();
	HashMap<String, LibraryMember> readMemberMap();
	void saveNewMember(LibraryMember member);
	void deleteMember(String memberId);
	void saveBooksMap(Map<String, Book> books);

    void saveAuthor(Author author);
	List<Author> readAuthorList();
}

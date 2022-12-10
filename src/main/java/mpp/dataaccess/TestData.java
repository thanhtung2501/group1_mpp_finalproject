package mpp.dataaccess;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mpp.business.model.Role;
import mpp.business.model.*;

/**
 * This class loads data into the data repository and also
 * sets up the storage units that are used in the application.
 * The main method in this class must be run once (and only
 * once) before the rest of the application can work properly.
 * It will create three serialized objects in the mpp.dataaccess.storage
 * folder.
 * 
 *
 */
public class TestData {
	
	
	public static void main(String[] args) {
		TestData td = new TestData();
		td.bookData();
		td.libraryMemberData();
		td.userData();
		td.authorData();

		DataAccess da = new DataAccessFacade();
		System.out.println(da.readBooksMap());
		System.out.println(da.readUserMap());
	}
	///create books
	public void bookData() {
		allBooks.get(0).addCopy();
		allBooks.get(0).addCopy();
		allBooks.get(1).addCopy();
		allBooks.get(3).addCopy();
		allBooks.get(2).addCopy();
		allBooks.get(2).addCopy();
		DataAccessFacade.loadBookMap(allBooks);
	}
	
	public void userData() {
		DataAccessFacade.loadUserMap(allUsers);
	}

	public void authorData() {
		DataAccessFacade.loadAuthorList(allAuthors);
	}
	
	//create library members
	public void libraryMemberData() {
		LibraryMember libraryMember = new LibraryMember("1001", "Andy", "Rogers", "6412232211", addresses.get(4));
		CheckoutRecordEntry checkoutRecordEntry = new CheckoutRecordEntry("23-11451","1", LocalDate.now().minusMonths(2),LocalDate.now().minusMonths(1),20.0, LocalDate.now());
		CheckoutRecord checkoutRecord = new CheckoutRecord();
		checkoutRecord.addCheckoutRecordEntry(checkoutRecordEntry);
		libraryMember.setCheckoutRecord(checkoutRecord);
		members.add(libraryMember);

		libraryMember = new LibraryMember("1002", "Drew", "Stevens", "7029982414", addresses.get(5));
		members.add(libraryMember);
		
		libraryMember = new LibraryMember("1003", "Sarah", "Eagleton", "4512348811", addresses.get(6));
		members.add(libraryMember);
		
		libraryMember = new LibraryMember("1004", "Ricardo", "Montalbahn", "6414722871", addresses.get(7));
		members.add(libraryMember);




		DataAccessFacade.loadMemberMap(members);	
	}
	
	///////////// DATA //////////////
	List<LibraryMember> members = new ArrayList<LibraryMember>();
	@SuppressWarnings("serial")
	
	List<Address> addresses = new ArrayList<Address>() {
		{
			add(new Address("101 S. mpp.Main", "Fairfield", "IA", "52556"));
			add(new Address("51 S. George", "Georgetown", "MI", "65434"));
			add(new Address("23 Headley Ave", "Seville", "Georgia", "41234"));
			add(new Address("1 N. Baton", "Baton Rouge", "LA", "33556"));
			add(new Address("5001 Venice Dr.", "Los Angeles", "CA", "93736"));
			add(new Address("1435 Channing Ave", "Palo Alto", "CA", "94301"));
			add(new Address("42 Dogwood Dr.", "Fairfield", "IA", "52556"));
			add(new Address("501 Central", "Mountain View", "CA", "94707"));
		}
	};
	@SuppressWarnings("serial")
	public List<Author> allAuthors = new ArrayList<Author>() {
		{
			add(new Author("Joe", "Thomas", "6414452123", addresses.get(0), "A happy man is he."));
			add(new Author("Sandra", "Thomas", "6414452123", addresses.get(0), "A happy wife is she."));
			add(new Author("Nirmal", "Pugh", "6419193223", addresses.get(1), "Thinker of thoughts."));
			add(new Author("Andrew", "Cleveland", "9764452232", addresses.get(2), "Author of childrens' books."));
			add(new Author("Sarah", "Connor", "1234222663", addresses.get(3), "Known for her clever style."));
		}
	};
	
	@SuppressWarnings("serial")
	List<Book> allBooks = new ArrayList<Book>() {
		{
			add(new Book("23-11451", "The Big Fish", 21, Arrays.asList(allAuthors.get(0), allAuthors.get(1))));
			add(new Book("28-12331", "Antartica", 7, Arrays.asList(allAuthors.get(2))));
			add(new Book("99-22223", "Thinking Java", 21, Arrays.asList(allAuthors.get(3))));
			add(new Book("48-56882", "Jimmy's First Day of School", 7, Arrays.asList(allAuthors.get(4))));		
		}
	};
	
	@SuppressWarnings("serial")
	List<User> allUsers = new ArrayList<User>() {
		{
			add(new User("101", "xyz", Role.LIBRARIAN));
			add(new User("102", "abc", Role.ADMIN));
			add(new User("103", "111", Role.BOTH));
			add(new User("tung", "111", Role.BOTH));
			add(new User("tu", "111", Role.BOTH));
			add(new User("dung", "111", Role.BOTH));
		}
	};



	List<CheckoutRecordEntry> allCheckoutRecordEntry = new ArrayList<CheckoutRecordEntry>(){
		{
			add(new CheckoutRecordEntry("23-11451","1", LocalDate.now().minusMonths(2),LocalDate.now().minusMonths(1),20.0, LocalDate.now()));
			add(new CheckoutRecordEntry("23-11451","2", LocalDate.now().minusMonths(2),LocalDate.now().minusMonths(1),20.0, LocalDate.now()));
			add(new CheckoutRecordEntry("23-11451","3", LocalDate.now().minusMonths(2),LocalDate.now().minusMonths(1),20.0, LocalDate.now()));
		}
	};
}

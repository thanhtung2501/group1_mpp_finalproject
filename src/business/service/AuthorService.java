package business.service;

import business.model.Address;
import business.model.Author;
import business.model.Person;

public class AuthorService extends AbstractLibraryService {
    public Author addAuthor(String firstName, String lastName, Address address, String phoneNumber, boolean credential, String bio){
        Author author = new Author(firstName, lastName, phoneNumber, address, bio);
        saveAuthor(author);
        return author;
    }

    public void saveAuthor(Author author){
        dataAccess.saveAuthor(author);
    }
}

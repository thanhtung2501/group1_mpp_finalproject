package mpp.business.service;

import mpp.business.model.Address;
import mpp.business.model.Author;

import java.util.List;

public class AuthorService extends AbstractLibraryService {
    public Author addAuthor(String firstName, String lastName, Address address, String phoneNumber, boolean credential, String bio){
        Author author = new Author(firstName, lastName, phoneNumber, address, bio);
        saveAuthor(author);
        return author;
    }

    public void saveAuthor(Author author){
        dataAccess.saveAuthor(author);
    }

    public List<Author> findAllAuthors() {
        return dataAccess.readAuthorList();
    }
}

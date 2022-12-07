package business.service;


import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public class AbstractLibraryService implements LibraryService {
    protected DataAccess dataAccess = new DataAccessFacade();

}

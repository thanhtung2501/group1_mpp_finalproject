package business.service;

import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public class AbstractLibraryService {
    protected DataAccess dataAccess = new DataAccessFacade();
}

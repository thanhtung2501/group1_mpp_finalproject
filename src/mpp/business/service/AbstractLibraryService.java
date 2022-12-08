package mpp.business.service;

import mpp.dataaccess.DataAccess;
import mpp.dataaccess.DataAccessFacade;

public class AbstractLibraryService {
    protected DataAccess dataAccess = new DataAccessFacade();
}

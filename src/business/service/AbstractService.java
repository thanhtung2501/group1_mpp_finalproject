package business.service;


import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

public class AbstractService implements Service {
    protected DataAccess dataAccess = new DataAccessFacade();

}

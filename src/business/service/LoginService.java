package business.service;

import business.exception.LoginException;
import business.model.Role;
import business.model.User;
import business.service.impl.LibraryServiceImpl;
import business.util.Constant;

import java.util.Map;

public class LoginService extends AbstractLibraryService {
    public Role login(String username, String password) throws LoginException {
        Map<String, User> map = dataAccess.readUserMap();
        if(!map.containsKey(username)) {
            throw new LoginException(String.format(Constant.ERROR_USERNAME_WRONG, username));
        }
        String passwordFound = map.get(username).getPassword();

        if(!passwordFound.equals(password)) {
            throw new LoginException(Constant.ERROR_PASSWORD_WRONG);
        }
        return map.get(username).getRole();
    }
}

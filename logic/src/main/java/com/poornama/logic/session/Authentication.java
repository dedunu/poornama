package com.poornama.logic.session;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.User;
import com.poornama.api.security.PasswordHash;
import com.poornama.data.dao.UserDAO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 * @author dedunu
 */
@Service
public class Authentication {

    private static Logger log = GlobalLogger.getLogger();
    private static String className = Authentication.class.getName();

    /**
     * Returns true if username and password are valid
     *
     * @param username String
     * @param password String
     * @return true if username and password are valid
     */
    public boolean doAuthenticate(String username, String password) {
        log.debug("[" + className + "] doAuthenticate: doAuthenticate()");

        UserDAO userDAO = new UserDAO();
        User user = userDAO.getByUserName(username);
        String hashedPasswordDB = "";
        try {
            hashedPasswordDB = user.getPassword();
        } catch (NullPointerException ex) {
            log.debug("[" + className + "] doAuthenticate: Couldn't retrieve user");

        }
        PasswordHash passwordHash = new PasswordHash();
        String hashPassword = passwordHash.getHash(username, password);

        if (hashedPasswordDB.equals(hashPassword)) {
            log.info("[" + className + "] doAuthenticate: Login success " + username);
            return true;
        } else {
            log.info("[" + className + "] doAuthenticate: Login failed " + username);
            return false;
        }
    }
}

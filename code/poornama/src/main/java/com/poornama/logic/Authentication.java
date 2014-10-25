package com.poornama.logic;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.security.PasswordHash;
import com.poornama.data.dao.UserDAO;
import com.poornama.data.objects.User;
import org.apache.log4j.Logger;

/**
 * Created by dedunu on 10/23/14.
 */
public class Authentication {

    private static Logger log = GlobalLogger.getLogger();
    private static String className = Authentication.class.getName();

    public boolean doAuthenticate(String username, String password){
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

        if(hashedPasswordDB.equals(hashPassword)){
            log.info("[" + className + "] doAuthenticate: Login success " + username);
            return true;
        } else {
            log.info("[" + className + "] doAuthenticate: Login failed " + username);
            return false;
        }
    }
}

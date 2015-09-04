package com.poornama.logic;

import com.poornama.data.MetadataInitializer;
import com.poornama.data.dao.UserDAO;

/**
 * @author dedunu
 */
public class ApplicationInitializer {

    /**
     * Checks the user count and run metadata initializer to start the appilcation
     */
    public void initialize () {
        UserDAO userDAO = new UserDAO();
       if ( userDAO.getAll() == null || userDAO.getAll().size() == 0) {
           MetadataInitializer metadataInitializer = new MetadataInitializer();
           metadataInitializer.initialize();
       }
    }
}

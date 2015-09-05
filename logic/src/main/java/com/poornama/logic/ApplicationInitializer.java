package com.poornama.logic;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.data.MetadataInitializer;
import com.poornama.data.dao.UserDAO;
import org.apache.log4j.Logger;

/**
 * @author dedunu
 */
public class ApplicationInitializer {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = ApplicationInitializer.class.getName();
    /**
     * Checks the user count and run metadata initializer to start the appilcation
     */
    public void initialize () {
        try {
            UserDAO userDAO = new UserDAO();
            if (userDAO.getAll() == null || userDAO.getAll().size() == 0) {
                MetadataInitializer metadataInitializer = new MetadataInitializer();
                metadataInitializer.initialize();
            }
            log.debug("[" + className + "] initialize()");
        } catch (Exception e) {
            log.error("[" + className + "] initialize: Error in parsing initialization");
        }
    }
}

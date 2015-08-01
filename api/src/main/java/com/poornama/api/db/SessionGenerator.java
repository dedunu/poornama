package com.poornama.api.db;

import com.poornama.api.logging.GlobalLogger;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SessionGenerator {

    private static Logger log = GlobalLogger.getLogger();
    private static String className = SessionGenerator.class.getName();

    /**
     * Generates a session from SessionFactoryGenerator
     *
     * @return Session
     */
    public Session getSession() {
        log.debug("[" + className + "] getSession()");
        Session session;
        try {
            // Create a session for each and every request using SessionFactoryGenerator
            SessionFactory sessionFactory = SessionFactoryGenerator.getSessionFactory();
            session = sessionFactory.openSession();
        } catch (Exception e){
            log.error("[" + className + "] getSession(): error " + e.getMessage());
            // Returns null if error occured
            return null;
        }
        return session;
    }

}

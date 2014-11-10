package com.poornama.api.db;

import com.poornama.api.logging.GlobalLogger;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SessionGenerator {

    private static Logger log = GlobalLogger.getLogger();
    private static String className = SessionGenerator.class.getName();

    // Generates a session from SessionFactoryGenerator
    public Session getSession() {
        log.debug("[" + className + "] getSession()");
        Session session;
        try {
            SessionFactory sessionFactory = SessionFactoryGenerator.getSessionFactory();
            session = sessionFactory.openSession();
        } catch (Exception e){
            log.error("[" + className + "] getSession(): error " + e.getMessage());
            return null;
        }
        return session;
    }

}

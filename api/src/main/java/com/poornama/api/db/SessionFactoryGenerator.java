package com.poornama.api.db;

import com.poornama.api.logging.GlobalLogger;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * @author dedunu
 */
public class SessionFactoryGenerator {

    /**
     * This class returns singleton SessionFactory for
     * Hibernate sessions. Do not auto format this page.
     * Auto formatting move sessionFactory variable to
     * the top.
     */

    private static Logger log = GlobalLogger.getLogger();
    private static String className = SessionFactoryGenerator.class.getName();
    // Creates a singleton Session factory object
    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static ServiceRegistry serviceRegistry;

    /**
     * This class cannot be instantiated. Constructor
     * has been restricted.
     */
    private SessionFactoryGenerator() {
        log.debug("[" + className + "] constructor()");
    }

    /**
     * Creates a SessionFactory object using configuration files for SessionFactoryGenerator class
     *
     * @return SessionFactory for the SessionFactoryGenerator class
     */
    private static SessionFactory buildSessionFactory() {
        log.debug("[" + className + "] buildSessionFactory()");
        try {
            Configuration configuration = new Configuration();

            configuration.configure();
            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

            // Returns Session
            SessionFactory generatedSessionFactory = configuration.buildSessionFactory(serviceRegistry);
            return generatedSessionFactory;
        } catch (Throwable ex) {
            log.debug("[" + className + "] buildSessionFactory : throws an exception " + ex.getMessage());
            // Printing StackTrace and return null.
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Returns SessionFactory for DAO classes
     *
     * @return SessionFactory
     */
    public static SessionFactory getSessionFactory() {
        log.debug("[" + className + "] getSessionFactory()");
        return sessionFactory;
    }

    public static void shutdown() {
        log.debug("[" + className + "] shutdown()");
        // Close the session factory
        getSessionFactory().close();
    }
}

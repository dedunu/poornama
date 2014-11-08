package com.poornama.api.db;

import com.poornama.api.logging.GlobalLogger;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class SessionFactoryGenerator {

    /*
        This class returns singleton SessionFactory for
        Hibernate sessions.
     */

    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static Logger log = GlobalLogger.getLogger();
    private static String className = SessionFactoryGenerator.class.getName();
    private static ServiceRegistry serviceRegistry;

    /*
        This class cannot be instantiated. Constructor
        has been restricted.
     */
    private SessionFactoryGenerator() {
        log.debug("[" + className + "] constructor()");
    }

    private static SessionFactory buildSessionFactory() {
        log.debug("[" + className + "] buildSessionFactory()");
        try {
            Configuration configuration = new Configuration();

            configuration.configure();
            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

            // Returns Session
            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            log.debug("[" + className + "] buildSessionFactory :Throwed an exception " + ex.getMessage());
            // Printing StackTrace and return null.
            ex.printStackTrace();
            return null;
        }
    }

    public static SessionFactory getSessionFactory() {
        log.debug("[" + className + "] getSessionFactory()");
        return sessionFactory;
    }

    public static void shutdown() {
        log.debug("[" + className + "] shutdown()");
        getSessionFactory().close();
    }
}

package com.poornama.api.db;

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
    private static ServiceRegistry serviceRegistry;

    /*
        This class cannot be instantiated. Constructor
        has been restricted.
     */
    private SessionFactoryGenerator() {

    }

    private static SessionFactory buildSessionFactory() {
        try {
            Configuration configuration = new Configuration();

            configuration.configure();
            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

            // Returns Session
            return configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            // Printing StackTrace and return null.
            ex.printStackTrace();
            return null;
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}

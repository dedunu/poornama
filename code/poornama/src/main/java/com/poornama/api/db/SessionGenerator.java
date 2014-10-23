package com.poornama.api.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class SessionGenerator {

    // Generates a session from SessionFactoryGenerator
    public Session getSession() {
        SessionFactory sessionFactory = SessionFactoryGenerator.getSessionFactory();
        Session session = sessionFactory.openSession();
        return session;
    }

}

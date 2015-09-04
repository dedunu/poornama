package com.poornama.api.db;

import com.poornama.api.logging.GlobalLogger;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

/**
 * @author dedunu
 */
public class DatabaseSession {

    private static Logger log = GlobalLogger.getLogger();
    private static String className = DatabaseSession.class.getName();
    private Session session;

    /**
     * Initialize variables for the database session
     */
    public DatabaseSession() {
        log.debug("[" + className + "] constructor()");
        SessionGenerator sessionGenerator = new SessionGenerator();
        session = sessionGenerator.getSession();
    }

    /**
     * Sets session
     *
     * @param session Session
     */
    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * Returns the session
     *
     * @return Session
     */
    public Session getSession() {
        return session;
    }

    /**
     * Start transaction for database operations
     */
    public void beginTransaction() {
        log.debug("[" + className + "] beginTransaction()");
        this.session.getTransaction().begin();
    }

    /**
     * Commit the transaction after database operations
     */
    public void commitTransaction() {
        log.debug("[" + className + "] commitTransaction()");
        this.session.getTransaction().commit();
    }

    /**
     * Persists the provided object
     *
     * @param object Object
     */
    public void save(Object object) {
        log.debug("[" + className + "] save()");
        this.beginTransaction();
        this.session.save(object);
        this.commitTransaction();
        this.close();
    }

    /**
     * Delete the provided object
     *
     * @param object Object
     */
    public void delete(Object object) {
        log.debug("[" + className + "] delete()");
        this.beginTransaction();
        this.session.delete(object);
        this.commitTransaction();
        this.close();
    }

    /**
     * Update the provided object
     *
     * @param object Object
     */
    public void update(Object object) {
        log.debug("[" + className + "] update()");
        this.beginTransaction();
        this.session.update(object);
        this.commitTransaction();
        this.close();
    }

    /**
     * Save or update the provided object
     *
     * @param object Object
     */
    public void saveOrUpdate(Object object) {
        log.debug("[" + className + "] saveOrUpdate()");
        this.beginTransaction();
        this.session.saveOrUpdate(object);
        this.commitTransaction();
        this.close();
    }

    /**
     * Returns the object from the provided class
     *
     * @param clazz Class
     * @param id Serializable
     * @return Object
     */
    public Object getById(Class clazz, Serializable id) {
        log.debug("[" + className + "] getById()");
        this.beginTransaction();
        Object result = this.session.get(clazz, id);
        this.commitTransaction();
        this.close();
        return result;
    }

    /**
     * Returns the list of objects for the given class
     *
     * @param clazz Class
     * @return List
     */
    public List getAll(Class clazz) {
        log.debug("[" + className + "] getAll()");
        this.beginTransaction();
        Criteria criteria = this.session.createCriteria(clazz);
        List result = criteria.list();
        this.commitTransaction();
        this.close();
        return result;
    }

    /**
     * Create and return the criteria for the search queries
     *
     * @param clazz Class
     * @return Criteria
     */
    public Criteria createCriteria(Class clazz) {
        log.debug("[" + className + "] createCriteria()");
        return this.session.createCriteria(clazz);
    }

    /**
     * Close the database session
     */
    public void close() {
        log.debug("[" + className + "] close()");
        this.session.close();
    }
}

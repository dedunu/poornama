package com.poornama.api.db;

import com.poornama.api.logging.GlobalLogger;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dedunu on 10/22/14.
 */
public class DatabaseSession {

    private static Logger log = GlobalLogger.getLogger();
    private static String className = DatabaseSession.class.getName();
    private Session session;

    public DatabaseSession(){
        log.debug("[" + className + "] constructor()");
        SessionGenerator sessionGenerator = new SessionGenerator();
        session = sessionGenerator.getSession();
    }

    public void beginTransaction(){
        log.debug("[" + className + "] beginTransaction()");
        this.session.getTransaction().begin();
    }

    public void commitTransaction(){
        log.debug("[" + className + "] commitTransaction()");
        this.session.getTransaction().commit();
    }

    public void save(Object object){
        log.debug("[" + className + "] save()");
        this.session.save(object);
    }

    public void delete(Object object){
        log.debug("[" + className + "] delete()");
        this.session.delete(object);
    }

    public void update(Object object){
        log.debug("[" + className + "] update()");
        this.session.update(object);
    }

    public void saveOrUpdate(Object object){
        log.debug("[" + className + "] saveOrUpdate()");
        this.session.saveOrUpdate(object);
    }

    public Object getById(Class clazz, Serializable id ){
        log.debug("[" + className + "] getById()");
        return this.session.get(clazz, id);
    }

    public List getAll(Class clazz){
        log.debug("[" + className + "] getAll()");
        Criteria criteria = this.session.createCriteria(clazz);
        return criteria.list();
    }

    public Criteria createCriteria(Class clazz){
        log.debug("[" + className + "] createCriteria()");
        return this.session.createCriteria(clazz);
    }

    public void close(){
        log.debug("[" + className + "] close()");
        this.session.close();
    }
}

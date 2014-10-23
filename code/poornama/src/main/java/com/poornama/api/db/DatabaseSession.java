package com.poornama.api.db;

import org.hibernate.Criteria;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dedunu on 10/22/14.
 */
public class DatabaseSession {
    private Session session;

    public DatabaseSession(){
        SessionGenerator sessionGenerator = new SessionGenerator();
        session = sessionGenerator.getSession();
    }

    public void beginTransaction(){
        this.session.getTransaction().begin();
    }

    public void commitTransaction(){
        this.session.getTransaction().commit();
    }

    public void save(Object object){
        this.session.save(object);
    }

    public void delete(Object object){
        this.session.delete(object);
    }

    public void update(Object object){
        this.session.update(object);
    }

    public void saveOrUpdate(Object object){
        this.session.saveOrUpdate(object);
    }

    public Object getById(Class clazz, Serializable id ){
       return this.session.get(clazz, id);
    }

    public List getAll(Class clazz){
        Criteria criteria = this.session.createCriteria(clazz);
        return criteria.list();
    }

    public Criteria createCriteria(Class clazz){
        return this.session.createCriteria(clazz);
    }

    public void close(){
        this.session.close();
    }
}

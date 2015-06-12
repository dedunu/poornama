package com.poornama.data.dao;

import com.poornama.api.db.DatabaseSession;
import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.User;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import java.util.List;

/**
 * Created by dedunu on 10/22/14.
 */
public class UserDAO {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = UserDAO.class.getName();

    public UserDAO() {
        log.debug("[" + className + "] UserDAO: constructor()");
    }

    public void create(User user) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        databaseSession.save(user);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] create()");
    }

    public void delete(User user) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        databaseSession.delete(user);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] delete()");
    }

    public void update(User user) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        databaseSession.update(user);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] update()");
    }

    public User getById(int id) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        User user = (User) databaseSession.getById(User.class, id);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] getById()");
        return user;
    }

    public User getById(String id) {
        int userId = 0;

        try {
            userId = Integer.parseInt(id);
        } catch (NumberFormatException ex) {
            return null;
        }
        return getById(userId);
    }

    public User getByUserName(String userName) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        Criteria criteria = databaseSession.createCriteria(User.class);
        SimpleExpression simpleExpression = Restrictions.eq("userName", userName);
        User user = (User) criteria.add(simpleExpression)
                .uniqueResult();
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] getByUserName()");
        return user;
    }

    public List<User> getAll() {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        List<User> userList = databaseSession.getAll(User.class);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] getAll()");
        return userList;
    }
}

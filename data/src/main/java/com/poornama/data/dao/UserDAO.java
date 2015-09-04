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
 * @author dedunu
 */
public class UserDAO {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = UserDAO.class.getName();

    /**
     * Create user
     *
     * @param user User
     */
    public void create(User user) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.save(user);
        log.debug("[" + className + "] create()");
    }

    /**
     * Delete user
     *
     * @param user User
     */
    public void delete(User user) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.delete(user);
        log.debug("[" + className + "] delete()");
    }

    /**
     * Update user
     *
     * @param user User
     */
    public void update(User user) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.update(user);
        log.debug("[" + className + "] update()");
    }

    /**
     * Return user from id
     *
     * @param id int
     * @return User
     */
    public User getById(int id) {
        DatabaseSession databaseSession = new DatabaseSession();
        User user = (User) databaseSession.getById(User.class, id);
        log.debug("[" + className + "] getById()");
        return user;
    }

    /**
     * Return user from id
     *
     * @param id String
     * @return User
     */
    public User getById(String id) {
        int userId = 0;

        try {
            userId = Integer.parseInt(id);
        } catch (NumberFormatException ex) {
            return null;
        }
        return getById(userId);
    }

    /**
     * Return user from userName
     *
     * @param userName String
     * @return User
     */
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

    /**
     * Return all the users
     *
     * @return List&lt;User&gt;
     */
    public List<User> getAll() {
        DatabaseSession databaseSession = new DatabaseSession();
        List<User> userList = databaseSession.getAll(User.class);
        log.debug("[" + className + "] getAll()");
        return userList;
    }
}

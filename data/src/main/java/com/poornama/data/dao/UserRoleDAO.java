package com.poornama.data.dao;

import com.poornama.api.db.DatabaseSession;
import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.UserRole;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import java.util.List;

/**
 * @author dedunu
 */
public class UserRoleDAO {

    private static Logger log = GlobalLogger.getLogger();
    private static String className = UserRoleDAO.class.getName();

    /**
     * Create user role
     *
     * @param userRole UserRole
     */
    public void create(UserRole userRole) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.save(userRole);
        log.debug("[" + className + "] create()");
    }

    /**
     * Delete user role
     *
     * @param userRole UserRole
     */
    public void delete(UserRole userRole) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.delete(userRole);
        log.debug("[" + className + "] delete()");
    }

    /**
     * Update user role
     *
     * @param userRole UserRole
     */
    public void update(UserRole userRole) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.update(userRole);
        log.debug("[" + className + "] update()");
    }

    /**
     * Return user role by id
     *
     * @param id int
     * @return UserRole
     */
    public UserRole getById(int id) {
        DatabaseSession databaseSession = new DatabaseSession();
        UserRole userRole = (UserRole) databaseSession.getById(UserRole.class, id);
        log.debug("[" + className + "] getById()");
        return userRole;
    }

    /**
     * Return user role by name
     *
     * @param name String
     * @return UserRole
     */
    public UserRole getByName(String name) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        Criteria criteria = databaseSession.createCriteria(UserRole.class);
        SimpleExpression simpleExpression = Restrictions.eq("name", name);
        UserRole userRole = (UserRole) criteria.add(simpleExpression)
                .uniqueResult();
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] getByName()");
        return userRole;
    }

    /**
     * Return all the user roles
     *
     * @return List&lt;UserRole&gt;
     */
    public List<UserRole> getAll() {
        DatabaseSession databaseSession = new DatabaseSession();
        List<UserRole> userRoleList = databaseSession.getAll(UserRole.class);
        log.debug("[" + className + "] getAll()");
        return userRoleList;
    }
}

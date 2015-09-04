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

    public UserRoleDAO() {
        log.debug("[" + className + "] UserDAO: constructor()");
    }

    public void create(UserRole userRole) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.save(userRole);
        log.debug("[" + className + "] create()");
    }

    public void delete(UserRole userRole) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.delete(userRole);
        log.debug("[" + className + "] delete()");
    }

    public void update(UserRole userRole) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.update(userRole);
        log.debug("[" + className + "] update()");
    }

    public UserRole getById(int id) {
        DatabaseSession databaseSession = new DatabaseSession();
        UserRole userRole = (UserRole) databaseSession.getById(UserRole.class, id);
        log.debug("[" + className + "] getById()");
        return userRole;
    }

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

    public List<UserRole> getAll() {
        DatabaseSession databaseSession = new DatabaseSession();
        List<UserRole> userRoleList = databaseSession.getAll(UserRole.class);
        log.debug("[" + className + "] getAll()");
        return userRoleList;
    }
}

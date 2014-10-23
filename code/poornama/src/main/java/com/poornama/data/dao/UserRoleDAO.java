package com.poornama.data.dao;

import com.poornama.api.db.DatabaseSession;
import com.poornama.api.logging.GlobalLogger;
import com.poornama.data.objects.UserRole;
import org.apache.log4j.Logger;

/**
 * Created by dedunu on 10/23/14.
 */
public class UserRoleDAO {

    private static Logger log = GlobalLogger.getLogger();
    private static String className = UserRoleDAO.class.getName();

    public UserRoleDAO() {
        log.debug("[" + className + "] UserDAO: constructor()");
    }

    public void create(UserRole userRole) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        databaseSession.save(userRole);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] create()");
    }

    public void delete(UserRole userRole) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        databaseSession.delete(userRole);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] delete()");
    }

    public void update(UserRole userRole) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        databaseSession.update(userRole);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] update()");
    }

    public UserRole getById(int id) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        UserRole userRole = (UserRole) databaseSession.getById(UserRole.class, id);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] getById()");
        return userRole;
    }
}

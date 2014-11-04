package com.poornama.data.dao;

import com.poornama.api.db.DatabaseSession;
import com.poornama.api.logging.GlobalLogger;
import com.poornama.data.objects.EmployeeType;
import org.apache.log4j.Logger;

/**
 * Created by dedunu on 11/4/14.
 */
public class EmployeeTypeDAO {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = EmployeeTypeDAO.class.getName();

    public EmployeeTypeDAO() {
        log.debug("[" + className + "] EmployeeTypeDAO: constructor()");
    }

    public void create(EmployeeType employeeType) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        databaseSession.save(employeeType);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] create()");
    }

    public void delete(EmployeeType employeeType) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        databaseSession.delete(employeeType);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] delete()");
    }

    public void update(EmployeeType employeeType) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        databaseSession.update(employeeType);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] update()");
    }

    public EmployeeType getById(int id) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        EmployeeType employeeType = (EmployeeType) databaseSession.getById(EmployeeType.class, id);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] getById()");
        return employeeType;
    }
}

package com.poornama.data.dao;

import com.poornama.api.db.DatabaseSession;
import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.EmployeeType;
import org.apache.log4j.Logger;

import java.util.List;

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

    public List<EmployeeType> getAll() {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        List<EmployeeType> employeeTypeList = databaseSession.getAll(EmployeeType.class);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] getAll()");
        return employeeTypeList;
    }
}

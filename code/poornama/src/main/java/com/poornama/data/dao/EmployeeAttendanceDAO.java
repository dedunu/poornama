package com.poornama.data.dao;

import com.poornama.api.db.DatabaseSession;
import com.poornama.api.logging.GlobalLogger;
import com.poornama.data.objects.EmployeeAttendance;
import org.apache.log4j.Logger;

/**
 * Created by dedunu on 11/4/14.
 */
public class EmployeeAttendanceDAO {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = EmployeeAttendanceDAO.class.getName();

    public EmployeeAttendanceDAO() {
        log.debug("[" + className + "] EmployeeAttendanceDAO: constructor()");
    }

    public void create(EmployeeAttendance employeeAttendance) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        databaseSession.save(employeeAttendance);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] create()");
    }

    public void delete(EmployeeAttendance employeeAttendance) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        databaseSession.delete(employeeAttendance);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] delete()");
    }

    public void update(EmployeeAttendance employeeAttendance) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        databaseSession.update(employeeAttendance);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] update()");
    }

    public EmployeeAttendance getById(int id) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        EmployeeAttendance employeeAttendance = (EmployeeAttendance) databaseSession.getById(EmployeeAttendance.class, id);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] getById()");
        return employeeAttendance;
    }
}

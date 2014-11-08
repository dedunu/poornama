package com.poornama.data.dao;

import com.poornama.api.db.DatabaseSession;
import com.poornama.api.logging.GlobalLogger;
import com.poornama.data.objects.EmployeeAttendance;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    public EmployeeAttendance getByIdDate(int id, Date date) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        EmployeeAttendance employeeAttendance = (EmployeeAttendance) databaseSession.getById(EmployeeAttendance.class, id);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] getById()");
        return employeeAttendance;
    }

    public EmployeeAttendance getByIdDate(String id, Date date) {
        int employeeId = 0;

        try {
            employeeId = Integer.parseInt(id);
        } catch (Exception e){
            log.debug("[" + className + "] getByIdDate(): error in parsing id");
        }
        return getByIdDate(employeeId, date);
    }

    public EmployeeAttendance getByIdDate(String id, String date) {
        Date attendanceDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        try {
            attendanceDate = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            log.error("[" + className + "] getByIdDate: Error in parsing dateOfBirth");
        }
        return getByIdDate(id, attendanceDate);
    }

    public EmployeeAttendance getByIdDate(int id, String date) {
        return getByIdDate(Integer.toString(id), date);
    }

    public EmployeeAttendance getById(Long id) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        EmployeeAttendance employeeAttendance = (EmployeeAttendance) databaseSession.getById(EmployeeAttendance.class, id);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] getById()");
        return employeeAttendance;
    }
}

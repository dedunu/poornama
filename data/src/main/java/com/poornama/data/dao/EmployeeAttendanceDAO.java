package com.poornama.data.dao;

import com.poornama.api.db.DatabaseSession;
import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.Employee;
import com.poornama.api.objects.EmployeeAttendance;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author dedunu
 */
public class EmployeeAttendanceDAO {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = EmployeeAttendanceDAO.class.getName();

    /**
     * Create employee attendance
     *
     * @param employeeAttendance EmployeeAttendance
     */
    public void create(EmployeeAttendance employeeAttendance) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.save(employeeAttendance);
        log.debug("[" + className + "] create()");
    }

    /**
     * Save or update employee attendance
     *
     * @param employeeAttendance EmployeeAttendance
     */
    public void saveOrUpdate(EmployeeAttendance employeeAttendance) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.saveOrUpdate(employeeAttendance);
        log.debug("[" + className + "] saveOrUpdate()");
    }

    /**
     * Delete employee attendance
     *
     * @param employeeAttendance EmployeeAttendance
     */
    public void delete(EmployeeAttendance employeeAttendance) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.delete(employeeAttendance);
        log.debug("[" + className + "] delete()");
    }

    /**
     * Update employee attendance
     *
     * @param employeeAttendance EmployeeAttendance
     */
    public void update(EmployeeAttendance employeeAttendance) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.update(employeeAttendance);
        log.debug("[" + className + "] update()");
    }

    /**
     * Returns the employee attendance from given employee and date
     *
     * @param employee Employee
     * @param date     Date
     * @return EmployeeAttendance
     */
    public EmployeeAttendance getByIdDate(Employee employee, Date date) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        Criteria criteria = databaseSession.createCriteria(EmployeeAttendance.class);
        Criterion employeeIdCriterion = Restrictions.eq("employee", employee);
        Criterion dateCriterion = Restrictions.eq("date", date);
        LogicalExpression logicalExpression = Restrictions.and(employeeIdCriterion, dateCriterion);
        criteria.add(logicalExpression);
        EmployeeAttendance employeeAttendance = (EmployeeAttendance) criteria.uniqueResult();
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] getById()");
        return employeeAttendance;
    }

    /**
     * Return the employee attendance from the give employeeId and the date
     *
     * @param employeeId int
     * @param date       Date
     * @return EmployeeAttendance
     */
    public EmployeeAttendance getByIdDate(int employeeId, Date date) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Employee employee = employeeDAO.getById(employeeId);
        return getByIdDate(employee, date);
    }

    /**
     * Return the employee attendance from the give employeeId and the date
     *
     * @param id   String
     * @param date Date
     * @return EmployeeAttendance
     */
    public EmployeeAttendance getByIdDate(String id, Date date) {
        int employeeId = 0;

        try {
            employeeId = Integer.parseInt(id);
        } catch (Exception e) {
            log.debug("[" + className + "] getByIdDate(): error in parsing id");
        }
        return getByIdDate(employeeId, date);
    }

    /**
     * Return the employee attendance from the give employeeId and the date
     *
     * @param id   String
     * @param date String
     * @return EmployeeAttendance
     */
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

    /**
     * Returns the employee attendance from employeeAttendanceId
     *
     * @param id Long
     * @return EmployeeAttendance
     */
    public EmployeeAttendance getById(Long id) {
        DatabaseSession databaseSession = new DatabaseSession();
        EmployeeAttendance employeeAttendance = (EmployeeAttendance) databaseSession.getById(EmployeeAttendance.class, id);
        log.debug("[" + className + "] getById()");
        return employeeAttendance;
    }
}

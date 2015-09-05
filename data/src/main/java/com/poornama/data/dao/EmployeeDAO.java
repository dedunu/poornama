package com.poornama.data.dao;


import com.poornama.api.db.DatabaseSession;
import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.Employee;
import com.poornama.api.objects.EmployeeAttendance;
import com.poornama.api.objects.EmployeeType;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import java.util.List;

/**
 * @author dedunu
 */
public class EmployeeDAO {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = EmployeeDAO.class.getName();

    /**
     * Delete employee
     *
     * @param employee Employee
     */
    public void delete(Employee employee) {
        SalaryDAO salaryDAO = new SalaryDAO();
        salaryDAO.deleteByEmployee(employee);
        EmployeeAttendanceDAO employeeAttendanceDAO = new EmployeeAttendanceDAO();
        employeeAttendanceDAO.deletebyEmployee(employee);
        JobDAO jobDAO = new JobDAO();
        jobDAO.deleteByEmployee(employee);
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.delete(employee);
        log.debug("[" + className + "] delete()");
    }

    /**
     * Update employee
     *
     * @param employee Employee
     */
    public void update(Employee employee) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.update(employee);
        log.debug("[" + className + "] update()");
    }

    /**
     * Return employee by Id
     *
     * @param id int
     * @return Employee
     */
    public Employee getById(int id) {
        DatabaseSession databaseSession = new DatabaseSession();
        Employee employee = (Employee) databaseSession.getById(Employee.class, id);
        log.debug("[" + className + "] getById()");
        return employee;
    }

    /**
     * Return employee by Id
     *
     * @param id String
     * @return Employee
     */
    public Employee getById(String id) {
        int employeeId;
        Employee employee = null;
        try {
            employeeId = Integer.parseInt(id);
            employee = getById(employeeId);
        } catch (Exception e) {
            log.debug("[" + className + "] getById(String): failed retrieving employee");
        }
        return employee;
    }

    /**
     * Create employee
     *
     * @param employee Employee
     */
    public void create(Employee employee) {
        // Initialize Database session
        DatabaseSession databaseSession = new DatabaseSession();
        // Save the employee object in database using database session
        databaseSession.save(employee);
        log.debug("[" + className + "] create()");
    }

    /**
     * List all the employees from the given employee type
     *
     * @param employeeType EmployeeType
     * @return List&lt;Employee&gt;
     */
    public List<Employee> getByEmployeeType(EmployeeType employeeType) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        Criteria criteria = databaseSession.createCriteria(Employee.class);
        SimpleExpression simpleExpression = Restrictions.eq("employeeType", employeeType);
        List<Employee> employeeList = criteria.add(simpleExpression).list();
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] getByEmployeeType()");
        return employeeList;
    }

    /**
     * Search employees from the first name
     *
     * @param firstName String
     * @return List&lt;Employee&gt;
     */
    public List<Employee> searchByFirstName(String firstName) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        Criteria criteria = databaseSession.createCriteria(Employee.class);
        SimpleExpression simpleExpression = Restrictions.like("firstName", firstName + "%");
        criteria.addOrder(Order.asc("firstName"));
        List<Employee> employeeList = criteria.add(simpleExpression).list();
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] searchByFirstName()");
        return employeeList;
    }

    /**
     * Return all the employees as a list
     *
     * @return List&lt;Employee&gt;
     */
    public List<Employee> getAll() {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        Criteria criteria = databaseSession.createCriteria(Employee.class);
        criteria.addOrder(Order.asc("firstName"));
        List<Employee> employeeList = criteria.list();
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] getAll()");
        return employeeList;
    }
}

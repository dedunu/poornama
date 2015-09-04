package com.poornama.data.dao;


import com.poornama.api.db.DatabaseSession;
import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.Employee;
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

    public EmployeeDAO() {
        log.debug("[" + className + "] EmployeeDAO: constructor()");
    }


    public void delete(Employee employee) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.delete(employee);
        log.debug("[" + className + "] delete()");
    }

    public void deleteById(int id) {
        Employee employee = getById(id);
        delete(employee);
        log.debug("[" + className + "] deleteById()");
    }

    public void update(Employee employee) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.update(employee);
        log.debug("[" + className + "] update()");
    }

    public Employee getById(int id) {
        DatabaseSession databaseSession = new DatabaseSession();
        Employee employee = (Employee) databaseSession.getById(Employee.class, id);
        log.debug("[" + className + "] getById()");
        return employee;
    }

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

    public void create(Employee employee) {
        // Initialize Database session
        DatabaseSession databaseSession = new DatabaseSession();
        // Save the employee object in database using database session
        databaseSession.save(employee);
        log.debug("[" + className + "] create()");
    }

    public Employee getByFirstName(String firstName) {
        // Initialize Database session
        DatabaseSession databaseSession = new DatabaseSession();
        // Start transactions
        databaseSession.beginTransaction();
        // Creates a criteria for the Employee Class
        Criteria criteria = databaseSession.createCriteria(Employee.class);
        // Creates the condition as an expression for firstName
        SimpleExpression simpleExpression = Restrictions.eq("firstName", firstName);
        // Grabs the unique result
        Employee employee = (Employee) criteria.add(simpleExpression)
                .uniqueResult();
        // Commit the transaction and close the database session
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] getByFirstName()");
        //Returns employee object
        return employee;
    }

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

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
 * Created by dedunu on 11/4/14.
 */
public class EmployeeDAO {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = EmployeeDAO.class.getName();

    public EmployeeDAO() {
        log.debug("[" + className + "] EmployeeDAO: constructor()");
    }

    public void create(Employee employee) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        databaseSession.save(employee);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] create()");
    }

    public void delete(Employee employee) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        databaseSession.delete(employee);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] delete()");
    }

    public void deleteById(int id) {
        Employee employee = getById(id);
        delete(employee);
        log.debug("[" + className + "] deleteById()");
    }

    public void update(Employee employee) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        databaseSession.update(employee);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] update()");
    }

    public Employee getById(int id) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        Employee employee = (Employee) databaseSession.getById(Employee.class, id);
        databaseSession.commitTransaction();
        databaseSession.close();
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

    public Employee getByFirstName(String firstName) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        Criteria criteria = databaseSession.createCriteria(Employee.class);
        SimpleExpression simpleExpression = Restrictions.eq("firstName", firstName);
        Employee employee = (Employee) criteria.add(simpleExpression)
                .uniqueResult();
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] getByFirstName()");
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

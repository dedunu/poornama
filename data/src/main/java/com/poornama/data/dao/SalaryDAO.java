package com.poornama.data.dao;

import com.poornama.api.db.DatabaseSession;
import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.Employee;
import com.poornama.api.objects.Salary;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import java.util.Date;
import java.util.List;

/**
 * @author dedunu
 */
public class SalaryDAO {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = SalaryDAO.class.getName();

    /**
     * Create salary entry
     *
     * @param salary Salary
     */
    public void create(Salary salary) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.save(salary);
        log.debug("[" + className + "] create()");
    }

    /**
     * Delete salary entry
     *
     * @param salary Salary
     */
    public void delete(Salary salary) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.delete(salary);
        log.debug("[" + className + "] delete()");
    }

    /**
     * Save or update salary entry
     *
     * @param salary Salary
     */
    public void saveOrUpdate(Salary salary) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.saveOrUpdate(salary);
        log.debug("[" + className + "] saveOrUpdate()");
    }

    /**
     * Update salary entry
     *
     * @param salary Salary
     */
    public void update(Salary salary) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.update(salary);
        log.debug("[" + className + "] update()");
    }

    /**
     * Returns the salary from the id
     *
     * @param id int
     * @return Salary
     */
    public Salary getById(int id) {
        DatabaseSession databaseSession = new DatabaseSession();
        Salary salary = (Salary) databaseSession.getById(
                Salary.class, id);
        log.debug("[" + className + "] getById()");
        return salary;
    }

    /**
     * Return the salary from the employee and the date
     *
     * @param employee Employee
     * @param date     Date
     * @return Salary
     */
    public Salary getByEmployeeDate(Employee employee, Date date) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        Criteria criteria = databaseSession.createCriteria(Salary.class);
        Criterion employeeIdCriterion = Restrictions.eq("employee", employee);
        Criterion dateCriterion = Restrictions.eq("date", date);
        LogicalExpression logicalExpression = Restrictions.and(employeeIdCriterion, dateCriterion);
        criteria.add(logicalExpression);
        Salary salary = (Salary) criteria.uniqueResult();
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] getByEmployeeDate()");
        return salary;
    }

    /**
     * Return all the salary entries as a list
     *
     * @return List&lt;Salary&gt;
     */
    @SuppressWarnings("unchecked")
    public List<Salary> getAll() {
        DatabaseSession databaseSession = new DatabaseSession();
        List<Salary> salaryList = databaseSession
                .getAll(Salary.class);
        log.debug("[" + className + "] getAll()");
        return salaryList;
    }
}

package com.poornama.data.dao;

import com.poornama.api.db.DatabaseSession;
import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.EmployeeType;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import java.util.List;

/**
 * @author dedunu
 */
public class EmployeeTypeDAO {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = EmployeeTypeDAO.class.getName();

    /**
     * Create employee type
     *
     * @param employeeType EmployeeType
     */
    public void create(EmployeeType employeeType) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.save(employeeType);
        log.debug("[" + className + "] create()");
    }

    /**
     * Delete employee type
     *
     * @param employeeType EmployeeType
     */
    public void delete(EmployeeType employeeType) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.delete(employeeType);
        log.debug("[" + className + "] delete()");
    }

    /**
     * Update employee type
     *
     * @param employeeType EmployeeType
     */
    public void update(EmployeeType employeeType) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.update(employeeType);
        log.debug("[" + className + "] update()");
    }

    /**
     * Returns the employee type from given id
     *
     * @param id int
     * @return EmployeeType
     */
    public EmployeeType getById(int id) {
        DatabaseSession databaseSession = new DatabaseSession();
        EmployeeType employeeType = (EmployeeType) databaseSession.getById(EmployeeType.class, id);
        log.debug("[" + className + "] getById()");
        return employeeType;
    }

    /**
     * Returns the employee type from the give name
     *
     * @param name String
     * @return EmployeeType
     */
    public EmployeeType getByName(String name) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        Criteria criteria = databaseSession.createCriteria(EmployeeType.class);
        SimpleExpression simpleExpression = Restrictions.eq("name", name);
        EmployeeType employeeType = (EmployeeType) criteria.add(simpleExpression)
                .uniqueResult();
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] getByName()");
        return employeeType;
    }

    /**
     * Returns all the employee type as a list
     *
     * @return List&lt;EmployeeType&gt;
     */
    public List<EmployeeType> getAll() {
        DatabaseSession databaseSession = new DatabaseSession();
        List<EmployeeType> employeeTypeList = databaseSession.getAll(EmployeeType.class);
        log.debug("[" + className + "] getAll()");
        return employeeTypeList;
    }
}

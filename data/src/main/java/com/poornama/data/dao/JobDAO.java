package com.poornama.data.dao;

import com.poornama.api.db.DatabaseSession;
import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.Employee;
import com.poornama.api.objects.Job;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import java.util.Date;
import java.util.List;

/**
 * @author dedunu
 */
public class JobDAO {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = JobDAO.class.getName();

    /**
     * Create the job
     *
     * @param job Job
     */
    public void create(Job job) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.save(job);
        log.debug("[" + className + "] create()");
    }

    /**
     * Delete the job
     *
     * @param job Job
     */
    public void delete(Job job) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.delete(job);
        log.debug("[" + className + "] delete()");
    }

    /**
     * Update the job
     *
     * @param job Job
     */
    public void update(Job job) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.update(job);
        log.debug("[" + className + "] update()");
    }

    /**
     * Returns the job from the given id
     *
     * @param id int
     * @return Job
     */
    public Job getById(int id) {
        DatabaseSession databaseSession = new DatabaseSession();
        Job job = (Job) databaseSession.getById(
                Job.class, id);
        log.debug("[" + className + "] getById()");
        return job;
    }

    /**
     * Return the job from given String id
     *
     * @param id String
     * @return Job
     */
    public Job getById(String id) {
        int jobId = 0;
        try {
            jobId = Integer.parseInt(id);
        } catch (NumberFormatException ex) {
            return null;
        }
        return getById(jobId);
    }

    /**
     * Return the list of jobs for the given driver
     *
     * @param employee Employee
     * @param fromDate Date
     * @param toDate   Date
     * @return List&lt;Job&gt;
     */
    public List<Job> getByDriverDate(Employee employee, Date fromDate, Date toDate) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        Criteria criteria = databaseSession.createCriteria(Job.class);
        Criterion employeeIdCriterion = Restrictions.eq("driver", employee);
        Criterion startDateCriterion = Restrictions.ge("startDate", fromDate);
        Criterion endDateCriterion = Restrictions.le("startDate", toDate);
        LogicalExpression logicalExpression = Restrictions.and(employeeIdCriterion, startDateCriterion);
        LogicalExpression logicalExpression1 = Restrictions.and(logicalExpression, endDateCriterion);
        criteria.add(logicalExpression1);
        List<Job> jobList = criteria.list();
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] getByDriverDate()");
        return jobList;
    }

    /**
     * Return the list of jobs for the given cleaner
     *
     * @param employee Employee
     * @param fromDate Date
     * @param toDate   Date
     * @return List&lt;Job&gt;
     */
    public List<Job> getByCleanerDate(Employee employee, Date fromDate, Date toDate) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        Criteria criteria = databaseSession.createCriteria(Job.class);
        Criterion employeeIdCriterion = Restrictions.eq("cleaner", employee);
        Criterion startDateCriterion = Restrictions.ge("startDate", fromDate);
        Criterion endDateCriterion = Restrictions.le("startDate", toDate);
        LogicalExpression logicalExpression = Restrictions.and(employeeIdCriterion, startDateCriterion);
        LogicalExpression logicalExpression1 = Restrictions.and(logicalExpression, endDateCriterion);
        criteria.add(logicalExpression1);
        List<Job> jobList = criteria.list();
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] getByDriverDate()");
        return jobList;
    }

    /**
     * Returns all the jobs
     *
     * @return List&lt;Job&gt;
     */
    @SuppressWarnings("unchecked")
    public List<Job> getAll() {
        DatabaseSession databaseSession = new DatabaseSession();
        List<Job> jobList = databaseSession
                .getAll(Job.class);
        log.debug("[" + className + "] getAll()");
        return jobList;
    }


}

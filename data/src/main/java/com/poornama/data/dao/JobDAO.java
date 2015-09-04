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

	public JobDAO() {
		log.debug("[" + className + "] JobDAO: constructor()");
	}

	public void create(Job job) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.save(job);
		log.debug("[" + className + "] create()");
	}

	public void delete(Job job) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.delete(job);
		log.debug("[" + className + "] delete()");
	}

	public void update(Job job) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.update(job);
		log.debug("[" + className + "] update()");
	}

	public Job getById(int id) {
		DatabaseSession databaseSession = new DatabaseSession();
		Job job = (Job) databaseSession.getById(
				Job.class, id);
		log.debug("[" + className + "] getById()");
		return job;
	}

	public Job getById(String id) {
		int jobId = 0;
		try {
			jobId = Integer.parseInt(id);
		} catch (NumberFormatException ex) {
			return null;
		}
		return getById(jobId);
	}

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

	@SuppressWarnings("unchecked")
	public List<Job> getAll() {
		DatabaseSession databaseSession = new DatabaseSession();
		List<Job> jobList = databaseSession
				.getAll(Job.class);
		log.debug("[" + className + "] getAll()");
		return jobList;
	}

	public List<Job> searchById(int id) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		Criteria criteria = databaseSession.createCriteria(Job.class);
		SimpleExpression simpleExpression = Restrictions.like("id", id + "%");
		criteria.addOrder(Order.asc("id"));
		List<Job> jobList = criteria.add(simpleExpression).list();
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] searchById()");
		return jobList;
	}

	public List<Job> searchById(String id) {
		int jobId = 0;
		try {
			jobId = Integer.parseInt(id);
		} catch (NumberFormatException ex) {
			return null;
		}
		return searchById(jobId);
	}
}

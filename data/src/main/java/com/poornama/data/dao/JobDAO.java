package com.poornama.data.dao;

import java.util.Date;
import java.util.List;

import com.poornama.api.objects.Employee;
import com.poornama.api.objects.JobTemplate;
import org.apache.log4j.Logger;

import com.poornama.api.db.DatabaseSession;
import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.Job;
import org.hibernate.Criteria;
import org.hibernate.criterion.*;

public class JobDAO {
	private static Logger log = GlobalLogger.getLogger();
	private static String className = JobDAO.class.getName();

	public JobDAO() {
		log.debug("[" + className + "] JobDAO: constructor()");
	}

	public void create(Job job) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		databaseSession.save(job);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] create()");
	}

	public void delete(Job job) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		databaseSession.delete(job);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] delete()");
	}

	public void update(Job job) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		databaseSession.update(job);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] update()");
	}

	public Job getById(int id) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		Job job = (Job) databaseSession.getById(
				Job.class, id);
		databaseSession.commitTransaction();
		databaseSession.close();
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
		databaseSession.beginTransaction();
		List<Job> jobList = databaseSession
				.getAll(Job.class);
		databaseSession.commitTransaction();
		databaseSession.close();
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

package com.poornama.data.dao;

import com.poornama.api.db.DatabaseSession;
import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.JobType;
import org.apache.log4j.Logger;

import java.util.List;

public class JobTypeDAO {
	private static Logger log = GlobalLogger.getLogger();
	private static String className = JobTypeDAO.class.getName();

	public JobTypeDAO() {
		log.debug("[" + className + "] JobTypeDAO: constructor()");
	}

	public void create(JobType jobType) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		databaseSession.save(jobType);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] create()");
	}

	public void delete(JobType jobType) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		databaseSession.delete(jobType);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] delete()");
	}

	public void update(JobType jobType) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		databaseSession.update(jobType);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] update()");
	}

	public JobType getById(int id) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		JobType jobType = (JobType) databaseSession.getById(
				JobType.class, id);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] getById()");
		return jobType;
	}

	@SuppressWarnings("unchecked")
	public List<JobType> getAll() {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		List<JobType> jobTypeList = databaseSession
				.getAll(JobType.class);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] getAll()");
		return jobTypeList;
	}
}

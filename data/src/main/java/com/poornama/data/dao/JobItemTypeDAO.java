package com.poornama.data.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.poornama.api.db.DatabaseSession;
import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.JobItemType;

public class JobItemTypeDAO {
	private static Logger log = GlobalLogger.getLogger();
	private static String className = JobItemTypeDAO.class.getName();

	public JobItemTypeDAO() {
		log.debug("[" + className + "] JobItemTypeDAO: constructor()");
	}

	public void create(JobItemType jobItemType) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		databaseSession.save(jobItemType);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] create()");
	}

	public void delete(JobItemType jobItemType) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		databaseSession.delete(jobItemType);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] delete()");
	}

	public void update(JobItemType jobItemType) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		databaseSession.update(jobItemType);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] update()");
	}

	public JobItemType getById(int id) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		JobItemType jobItemType = (JobItemType) databaseSession.getById(
				JobItemType.class, id);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] getById()");
		return jobItemType;
	}

	@SuppressWarnings("unchecked")
	public List<JobItemType> getAll() {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		List<JobItemType> jobItemTypeList = databaseSession
				.getAll(JobItemType.class);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] getAll()");
		return jobItemTypeList;
	}
}

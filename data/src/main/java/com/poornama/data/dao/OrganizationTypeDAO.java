package com.poornama.data.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import com.poornama.api.db.DatabaseSession;
import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.OrganizationType;
import com.poornama.api.objects.UserRole;

public class OrganizationTypeDAO {
	private static Logger log = GlobalLogger.getLogger();
	private static String className = OrganizationTypeDAO.class.getName();

	public OrganizationTypeDAO() {
		log.debug("[" + className + "] OrganizationTypeDAO: constructor()");
	}

	public void create(OrganizationType organizationType) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		databaseSession.save(organizationType);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] create()");
	}

	public void delete(OrganizationType organizationType) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		databaseSession.delete(organizationType);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] delete()");
	}

	public void update(OrganizationType organizationType) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		databaseSession.update(organizationType);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] update()");
	}

	public OrganizationType getById(int id) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		OrganizationType organizationType = (OrganizationType) databaseSession
				.getById(OrganizationType.class, id);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] getById()");
		return organizationType;
	}

	public OrganizationType getByName(String name) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		Criteria criteria = databaseSession
				.createCriteria(OrganizationType.class);
		SimpleExpression simpleExpression = Restrictions.eq("name", name);
		OrganizationType organizationType = (OrganizationType) criteria.add(
				simpleExpression).uniqueResult();
		databaseSession.close();
		log.debug("[" + className + "] getByName()");
		return organizationType;
	}

	@SuppressWarnings("unchecked")
	public List<OrganizationType> getAll() {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		List<OrganizationType> organizationTypeList = databaseSession
				.getAll(OrganizationType.class);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] getAll()");
		return organizationTypeList;
	}
}

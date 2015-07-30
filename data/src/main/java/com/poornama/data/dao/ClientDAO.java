package com.poornama.data.dao;

import com.poornama.api.db.DatabaseSession;
import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.Client;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import java.util.List;

public class ClientDAO {
	private static Logger log = GlobalLogger.getLogger();
	private static String className = ClientDAO.class.getName();

	public ClientDAO() {
		log.debug("[" + className + "] ClientDAO: constructor()");
	}

	public void create(Client client) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		databaseSession.save(client);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] create()");
	}

	public void delete(Client client) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		databaseSession.delete(client);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] delete()");
	}

	public void update(Client client) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		databaseSession.update(client);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] update()");
	}

	public Client getById(int id) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		Client client = (Client) databaseSession.getById(
				Client.class, id);
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] getById()");
		return client;
	}

	public Client getByOrganizationName(String organizationName) {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		Criteria criteria = databaseSession.createCriteria(Client.class);
		SimpleExpression simpleExpression = Restrictions.eq("organizationName", organizationName);
		Client client = (Client) criteria.add(simpleExpression)
				.uniqueResult();
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] getByOrganizationName()");
		return client;
	}

	@SuppressWarnings("unchecked")
	public List<Client> getAll() {
		DatabaseSession databaseSession = new DatabaseSession();
		databaseSession.beginTransaction();
		Criteria criteria = databaseSession.createCriteria(Client.class);
		criteria.addOrder(Order.asc("organizationName"));
		List<Client> clientList = criteria.list();
		databaseSession.commitTransaction();
		databaseSession.close();
		log.debug("[" + className + "] getAll()");
		return clientList;
	}
}

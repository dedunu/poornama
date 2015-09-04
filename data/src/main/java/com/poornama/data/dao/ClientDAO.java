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

/**
 * @author dedunu
 */
public class ClientDAO {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = ClientDAO.class.getName();

    /**
     * Creates the client
     *
     * @param client Client
     */
    public void create(Client client) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.save(client);
        log.debug("[" + className + "] create()");
    }

    /**
     * Deletes the client
     *
     * @param client Client
     */
    public void delete(Client client) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.delete(client);
        log.debug("[" + className + "] delete()");
    }

    /**
     * Updated the client
     *
     * @param client Client
     */
    public void update(Client client) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.update(client);
        log.debug("[" + className + "] update()");
    }

    /**
     * Returns the client from given id
     *
     * @param id int
     * @return Client
     */
    public Client getById(int id) {
        DatabaseSession databaseSession = new DatabaseSession();
        Client client = (Client) databaseSession.getById(
                Client.class, id);
        log.debug("[" + className + "] getById()");
        return client;
    }

    /**
     * Returns client from given organizationName
     *
     * @param organizationName String
     * @return Client
     */
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

    /**
     * Returns all the clients
     *
     * @return List&lt;Client&gt;
     */
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

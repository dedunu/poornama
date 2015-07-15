package com.poornama.data.dao;

import com.poornama.api.db.DatabaseSession;
import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.Configuration;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import java.util.List;

/**
 * Created by ddhananjaya on 7/15/15.
 */
public class ConfigurationDAO {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = ConfigurationDAO.class.getName();

    public ConfigurationDAO() {
        log.debug("[" + className + "] ConfigurationDAO: constructor()");
    }

    public void create(Configuration configuration) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        databaseSession.save(configuration);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] create()");
    }

    public void delete(Configuration configuration) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        databaseSession.delete(configuration);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] delete()");
    }

    public void update(Configuration configuration) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        databaseSession.update(configuration);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] update()");
    }

    public Configuration getById(int id) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        Configuration configuration = (Configuration) databaseSession.getById(
                Configuration.class, id);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] getById()");
        return configuration;
    }

    public Configuration getByName(String name) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        Criteria criteria = databaseSession.createCriteria(Configuration.class);
        SimpleExpression simpleExpression = Restrictions.eq("name", name);
        Configuration configuration = (Configuration) criteria.add(simpleExpression)
                .uniqueResult();
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] getByOrganizationName()");
        return configuration;
    }

    @SuppressWarnings("unchecked")
    public List<Configuration> getAll() {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        List<Configuration> configurationList = databaseSession
                .getAll(Configuration.class);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] getAll()");
        return configurationList;
    }
}
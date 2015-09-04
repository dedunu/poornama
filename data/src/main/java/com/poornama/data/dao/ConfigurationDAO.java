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
 * @author dedunu
 */
public class ConfigurationDAO {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = ConfigurationDAO.class.getName();

    /**
     * Create the configuration
     *
     * @param configuration Configuration
     */
    public void create(Configuration configuration) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.save(configuration);
        log.debug("[" + className + "] create()");
    }

    /**
     * Delete the configuration
     *
     * @param configuration Configuration
     */
    public void delete(Configuration configuration) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.delete(configuration);
        log.debug("[" + className + "] delete()");
    }

    /**
     * Update the configuration
     *
     * @param configuration Configuration
     */
    public void update(Configuration configuration) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.update(configuration);
        log.debug("[" + className + "] update()");
    }

    /**
     * Return the configuration from id
     *
     * @param id int
     * @return Configuration
     */
    public Configuration getById(int id) {
        DatabaseSession databaseSession = new DatabaseSession();
        Configuration configuration = (Configuration) databaseSession.getById(
                Configuration.class, id);
        log.debug("[" + className + "] getById()");
        return configuration;
    }

    /**
     * Return the configuration by name
     *
     * @param name String
     * @return Configuration
     */
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

    /**
     * Return all the configuration
     *
     * @return List&lt;Configuration&gt;
     */
    @SuppressWarnings("unchecked")
    public List<Configuration> getAll() {
        DatabaseSession databaseSession = new DatabaseSession();
        List<Configuration> configurationList = databaseSession
                .getAll(Configuration.class);
        log.debug("[" + className + "] getAll()");
        return configurationList;
    }
}

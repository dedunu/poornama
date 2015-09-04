package com.poornama.data.dao;

import com.poornama.api.db.DatabaseSession;
import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.JobTemplate;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * @author dedunu
 */
public class JobTemplateDAO {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = JobTemplateDAO.class.getName();

    /**
     * Create the job template
     *
     * @param jobTemplate JobTemplate
     */
    public void create(JobTemplate jobTemplate) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.save(jobTemplate);
        log.debug("[" + className + "] create()");
    }

    /**
     * Delete the job template
     *
     * @param jobTemplate JobTemplate
     */
    public void delete(JobTemplate jobTemplate) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.delete(jobTemplate);
        log.debug("[" + className + "] delete()");
    }

    /**
     * Update the job template
     *
     * @param jobTemplate JobTemplate
     */
    public void update(JobTemplate jobTemplate) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.update(jobTemplate);
        log.debug("[" + className + "] update()");
    }

    /**
     * Return the job template by given id
     *
     * @param id int
     * @return JobTemplate
     */
    public JobTemplate getById(int id) {
        DatabaseSession databaseSession = new DatabaseSession();
        JobTemplate jobTemplate = (JobTemplate) databaseSession.getById(
                JobTemplate.class, id);
        log.debug("[" + className + "] getById()");
        return jobTemplate;
    }

    /**
     * Return job template by given id
     *
     * @param id String
     * @return JobTemplate
     */
    public JobTemplate getById(String id) {
        int jobTemplateId = 0;
        try {
            jobTemplateId = Integer.parseInt(id);
        } catch (NumberFormatException ex) {
            return null;
        }
        return getById(jobTemplateId);
    }

    /**
     * Return a list of all the job template
     *
     * @return List&lt;JobTemplate&gt;
     */
    @SuppressWarnings("unchecked")
    public List<JobTemplate> getAll() {
        DatabaseSession databaseSession = new DatabaseSession();
        List<JobTemplate> jobTemplateList = databaseSession
                .getAll(JobTemplate.class);
        log.debug("[" + className + "] getAll()");
        return jobTemplateList;
    }
}

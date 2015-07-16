package com.poornama.data.dao;

import java.util.List;

import org.apache.log4j.Logger;

import com.poornama.api.db.DatabaseSession;
import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.JobTemplate;

public class JobTemplateDAO {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = JobTemplateDAO.class.getName();

    public JobTemplateDAO() {
        log.debug("[" + className + "] JobTemplateDAO: constructor()");
    }

    public void create(JobTemplate jobTemplate) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        databaseSession.save(jobTemplate);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] create()");
    }

    public void delete(JobTemplate jobTemplate) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        databaseSession.delete(jobTemplate);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] delete()");
    }

    public void update(JobTemplate jobTemplate) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        databaseSession.update(jobTemplate);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] update()");
    }

    public JobTemplate getById(int id) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        JobTemplate jobTemplate = (JobTemplate) databaseSession.getById(
                JobTemplate.class, id);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] getById()");
        return jobTemplate;
    }

    public JobTemplate getById(String id) {
        int jobTemplateId = 0;
        try {
            jobTemplateId = Integer.parseInt(id);
        } catch (NumberFormatException ex) {
            return null;
        }
        return getById(jobTemplateId);
    }

    @SuppressWarnings("unchecked")
    public List<JobTemplate> getAll() {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        List<JobTemplate> jobTemplateList = databaseSession
                .getAll(JobTemplate.class);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] getAll()");
        return jobTemplateList;
    }
}

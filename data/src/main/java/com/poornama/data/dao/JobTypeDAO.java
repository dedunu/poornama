package com.poornama.data.dao;

import com.poornama.api.db.DatabaseSession;
import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.JobType;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * @author dedunu
 */
public class JobTypeDAO {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = JobTypeDAO.class.getName();

    /**
     * Create the job type
     *
     * @param jobType JobType
     */
    public void create(JobType jobType) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.save(jobType);
        log.debug("[" + className + "] create()");
    }

    /**
     * Delete the job type
     *
     * @param jobType JobType
     */
    public void delete(JobType jobType) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.delete(jobType);
        log.debug("[" + className + "] delete()");
    }

    /**
     * Update the job type
     *
     * @param jobType JobType
     */
    public void update(JobType jobType) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.update(jobType);
        log.debug("[" + className + "] update()");
    }

    /**
     * Returns the job type by given id
     *
     * @param id int
     * @return JobType
     */
    public JobType getById(int id) {
        DatabaseSession databaseSession = new DatabaseSession();
        JobType jobType = (JobType) databaseSession.getById(
                JobType.class, id);
        log.debug("[" + className + "] getById()");
        return jobType;
    }

    /**
     * Return list of job types
     *
     * @return List&lt;JobType&gt;
     */
    @SuppressWarnings("unchecked")
    public List<JobType> getAll() {
        DatabaseSession databaseSession = new DatabaseSession();
        List<JobType> jobTypeList = databaseSession
                .getAll(JobType.class);
        log.debug("[" + className + "] getAll()");
        return jobTypeList;
    }
}

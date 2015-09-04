package com.poornama.logic.object;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.JobType;
import com.poornama.data.dao.JobTypeDAO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dedunu
 */
@Service
public class JobTypeLogic {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = JobTemplateLogic.class.getName();

    /**
     * Returns job type list for controller classes as select list
     *
     * @return job type list for controller classes as String
     */
    public String getJobTypeSelectList() {
        JobTypeDAO jobTypeDAO = new JobTypeDAO();
        List<JobType> jobTypeList = jobTypeDAO.getAll();

        String list = "";
        for (JobType jobType : jobTypeList) {
            list = list + "\t\t<option value =\"" + jobType.getId() + "\">" + jobType.getDisplayName() + "</option>\n";
        }
        log.debug("[" + className + "] getJobTypeSelectList()");
        return list;
    }
}

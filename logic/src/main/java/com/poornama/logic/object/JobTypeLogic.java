package com.poornama.logic.object;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.JobType;
import com.poornama.data.dao.JobTypeDAO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dedunu on 7/30/15.
 */
@Service
public class JobTypeLogic {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = JobTemplateLogic.class.getName();

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

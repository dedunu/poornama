package com.poornama.logic.object;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.JobTemplate;
import com.poornama.data.dao.JobTemplateDAO;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by ddhananjaya on 6/11/15.
 */
public class JobTemplateLogic {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = JobTemplateLogic.class.getName();

    public String getJobTemplateSelectList() {
        JobTemplateDAO jobTemplateDAO = new JobTemplateDAO();
        List<JobTemplate> jobTemplateList = jobTemplateDAO.getAll();

        String list = "";
        for (JobTemplate jobTemplate : jobTemplateList) {
            list = list + "\t\t<option value =\"" + jobTemplate.getId() + "\">" + jobTemplate.getDisplayName() + "</option>\n";
        }
        log.debug("[" + className + "] getCleanerSelectList()");
        return list;
    }

    public String getJobTemplateDetails(String jobTemplateId) {
        JobTemplateDAO jobTemplateDAO = new JobTemplateDAO();
        int parsedJobTemplateId = 0;

        try {
            parsedJobTemplateId = Integer.parseInt(jobTemplateId);
        } catch (Exception ex) {
            log.error("[" + className + "] getJobTemplateDetails() : parsing jobTemplateId to int failed.");
            return null;
        }

        JobTemplate jobTemplate = jobTemplateDAO.getById(parsedJobTemplateId);

        String result = "{ \n";
        result = result + "'labourCharges' : " + jobTemplate.getLabourCharges().toBigInteger() + ", \n";
        result = result + "'hireCharges' : " + jobTemplate.getHireCharges().toBigInteger() + ", \n";
        result = result + "'detentionCharges' : " + jobTemplate.getDetentionCharges().toBigInteger() + ", \n";
        result = result + "'dailyContainerCharges' : " + jobTemplate.getDailyContainerCharges().toBigInteger() + ", \n";
        result = result + "'freeHours' : " + jobTemplate.getFreeHours() + " \n";
        result = result + "}";

        return result;
    }
}

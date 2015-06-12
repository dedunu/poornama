package com.poornama.logic.object;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.JobTemplate;
import com.poornama.data.dao.JobTemplateDAO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.json.*;
import java.util.List;

/**
 * Created by ddhananjaya on 6/11/15.
 */
@Service
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

    public JsonObject getJobTemplateDetails(String jobTemplateId) {
        JobTemplateDAO jobTemplateDAO = new JobTemplateDAO();
        int parsedJobTemplateId = 0;

        try {
            parsedJobTemplateId = Integer.parseInt(jobTemplateId);
        } catch (Exception ex) {
            log.error("[" + className + "] getJobTemplateDetails() : parsing jobTemplateId to int failed.");

            JsonObject nullObject = Json.createObjectBuilder()
                    .add("labourCharges",0)
                    .add("hireCharges",0)
                    .add("hourlyDetentionCharges", 0)
                    .add("dailyContainerCharges", 0)
                    .add("freeHours", 0)
                    .build();
            return nullObject;
        }

        JobTemplate jobTemplate = jobTemplateDAO.getById(parsedJobTemplateId);

        JsonObject jsonObject = Json.createObjectBuilder()
                .add("labourCharges", jobTemplate.getLabourCharges().toBigInteger().toString())
                .add("hireCharges", jobTemplate.getHireCharges().toBigInteger().toString())
                .add("hourlyDetentionCharges", jobTemplate.getHourlyDetentionCharges().toBigInteger().toString())
                .add("dailyContainerCharges", jobTemplate.getDailyContainerCharges().toBigInteger().toString())
                .add("freeHours", Integer.toString(jobTemplate.getFreeHours()))
                .build();

        return jsonObject;
    }
}

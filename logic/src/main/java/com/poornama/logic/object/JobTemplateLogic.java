package com.poornama.logic.object;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.Client;
import com.poornama.api.objects.JobTemplate;
import com.poornama.api.objects.JobType;
import com.poornama.api.presentation.DataTableGenerator;
import com.poornama.api.presentation.Notification;
import com.poornama.api.presentation.NotificationType;
import com.poornama.data.dao.ClientDAO;
import com.poornama.data.dao.JobTemplateDAO;
import com.poornama.data.dao.JobTypeDAO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.json.Json;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by ddhananjaya on 6/11/15.
 */
@Service
public class JobTemplateLogic {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = JobTemplateLogic.class.getName();

    public Notification createJobTemplate(HttpServletRequest request) {
        JobTemplateDAO jobTemplateDAO = new JobTemplateDAO();
        JobTypeDAO jobTypeDAO = new JobTypeDAO();
        ClientDAO clientDAO = new ClientDAO();

        JobTemplate jobTemplate = new JobTemplate();
        Notification notification = new Notification();

        BigDecimal hireCharges;
        BigDecimal labourCharges;
        BigDecimal hourlyDetentionCharges;
        BigDecimal dailyContainerCharges;

        int freeHours = 0;
        int containerSize = 0;
        int distance = 0;

        Client client = new Client();
        JobType jobType = new JobType();

        try {
            jobType = jobTypeDAO.getById(Integer.parseInt(request.getParameter("jobType")));
        } catch (NumberFormatException e) {
            log.error("[" + className + "] createJobTemplate: Error in parsing job type id");
        } catch (Exception e) {
            log.error("[" + className + "] createJobTemplate: Error in retrieving job type");
        }

        try {
            client = clientDAO.getById(Integer.parseInt(request.getParameter("client")));
        } catch (NumberFormatException e) {
            log.error("[" + className + "] createJobTemplate: Error in parsing client id");
        } catch (Exception e) {
            log.error("[" + className + "] createJobTemplate: Error in retrieving client");
        }

        try {
            freeHours = Integer.parseInt(request.getParameter("freeHours"));
        } catch (NumberFormatException e) {
            log.error("[" + className + "] createJobTemplate: Error in parsing freeHours");
        }

        try {
            containerSize = Integer.parseInt(request.getParameter("containerSize"));
        } catch (NumberFormatException e) {
            log.error("[" + className + "] createJobTemplate: Error in parsing containerSize");
        }

        try {
            distance = Integer.parseInt(request.getParameter("distance"));
        } catch (NumberFormatException e) {
            log.error("[" + className + "] createJobTemplate: Error in parsing distance");
        }

        hireCharges = new BigDecimal(request.getParameter("hireCharges"));
        labourCharges = new BigDecimal(request.getParameter("labourCharges"));
        hourlyDetentionCharges = new BigDecimal(request.getParameter("hourlyDetentionCharges"));
        dailyContainerCharges = new BigDecimal(request.getParameter("dailyContainerCharges"));

        jobTemplate.setDisplayName(request.getParameter("displayName"));
        jobTemplate.setFromLocation(request.getParameter("fromLocation"));
        jobTemplate.setToLocation(request.getParameter("toLocation"));
        jobTemplate.setContainerSize(containerSize);
        jobTemplate.setHireCharges(hireCharges);
        jobTemplate.setLabourCharges(labourCharges);
        jobTemplate.setHourlyDetentionCharges(hourlyDetentionCharges);
        jobTemplate.setDailyContainerCharges(dailyContainerCharges);
        jobTemplate.setFreeHours(freeHours);
        jobTemplate.setDistance(distance);
        jobTemplate.setClient(client);
        jobTemplate.setJobType(jobType);

        try {
            jobTemplateDAO.create(jobTemplate);
            notification.setNotificationType(NotificationType.SUCCESS);
            notification.setMessage("Job template created successfully.");
            log.info("[" + className + "] createJobTemplate: created Job template");
        } catch (Exception e) {
            notification.setNotificationType(NotificationType.DANGER);
            notification.setMessage("Something went wrong with creating job template. Please try again.");
            log.error("[" + className + "] createJobTemplate: failed creating job template");
        }

        return notification;
    }


    public Notification editJobTemplate(HttpServletRequest request, String jobTemplateId) {
        JobTemplateDAO jobTemplateDAO = new JobTemplateDAO();
        JobTypeDAO jobTypeDAO = new JobTypeDAO();
        ClientDAO clientDAO = new ClientDAO();

        JobTemplate jobTemplate = jobTemplateDAO.getById(jobTemplateId);
        Notification notification = new Notification();

        BigDecimal hireCharges;
        BigDecimal labourCharges;
        BigDecimal hourlyDetentionCharges;
        BigDecimal dailyContainerCharges;

        int freeHours = 0;
        int containerSize = 0;
        int distance = 0;

        Client client = new Client();
        JobType jobType = new JobType();

        try {
            jobType = jobTypeDAO.getById(Integer.parseInt(request.getParameter("jobType")));
        } catch (NumberFormatException e) {
            log.error("[" + className + "] editJobTemplate: Error in parsing job type id");
        } catch (Exception e) {
            log.error("[" + className + "] editJobTemplate: Error in retrieving job type");
        }

        try {
            client = clientDAO.getById(Integer.parseInt(request.getParameter("client")));
        } catch (NumberFormatException e) {
            log.error("[" + className + "] editJobTemplate: Error in parsing client id");
        } catch (Exception e) {
            log.error("[" + className + "] editJobTemplate: Error in retrieving client");
        }

        try {
            freeHours = Integer.parseInt(request.getParameter("freeHours"));
        } catch (NumberFormatException e) {
            log.error("[" + className + "] editJobTemplate: Error in parsing freeHours");
        }

        try {
            containerSize = Integer.parseInt(request.getParameter("containerSize"));
        } catch (NumberFormatException e) {
            log.error("[" + className + "] editJobTemplate: Error in parsing containerSize");
        }

        try {
            distance = Integer.parseInt(request.getParameter("distance"));
        } catch (NumberFormatException e) {
            log.error("[" + className + "] editJobTemplate: Error in parsing distance");
        }

        hireCharges = new BigDecimal(request.getParameter("hireCharges"));
        labourCharges = new BigDecimal(request.getParameter("labourCharges"));
        hourlyDetentionCharges = new BigDecimal(request.getParameter("hourlyDetentionCharges"));
        dailyContainerCharges = new BigDecimal(request.getParameter("dailyContainerCharges"));

        jobTemplate.setDisplayName(request.getParameter("displayName"));
        jobTemplate.setFromLocation(request.getParameter("fromLocation"));
        jobTemplate.setToLocation(request.getParameter("toLocation"));
        jobTemplate.setContainerSize(containerSize);
        jobTemplate.setHireCharges(hireCharges);
        jobTemplate.setLabourCharges(labourCharges);
        jobTemplate.setHourlyDetentionCharges(hourlyDetentionCharges);
        jobTemplate.setDailyContainerCharges(dailyContainerCharges);
        jobTemplate.setFreeHours(freeHours);
        jobTemplate.setDistance(distance);
        jobTemplate.setClient(client);
        jobTemplate.setJobType(jobType);

        try {
            jobTemplateDAO.update(jobTemplate);
            notification.setNotificationType(NotificationType.SUCCESS);
            notification.setMessage("Job template updated successfully.");
            log.info("[" + className + "] editJobTemplate: updated Job template");
        } catch (Exception e) {
            notification.setNotificationType(NotificationType.DANGER);
            notification.setMessage("Something went wrong with updating job template. Please try again.");
            log.error("[" + className + "] editJobTemplate: failed updating job template");
        }

        return notification;
    }

    public Notification deleteJobTemplate(String jobTemplateId) {
        Notification notification = new Notification();
        JobTemplateDAO jobTemplateDAO = new JobTemplateDAO();
        try {
            JobTemplate jobTemplate = jobTemplateDAO.getById(jobTemplateId);
            jobTemplateDAO.delete(jobTemplate);
            notification.setNotificationType(NotificationType.SUCCESS);
            notification.setMessage("Deleted job template successfully");
        } catch (Exception e) {
            notification.setNotificationType(NotificationType.DANGER);
            notification.setMessage("Deleted job template failed. Please try again.");
        }

        return notification;
    }

    public String getJobTemplateTable() {
        List<JobTemplate> jobTemplateList;
        JobTemplateDAO jobTemplateDAO = new JobTemplateDAO();
        DataTableGenerator dataTableGenerator = new DataTableGenerator();
        String table;

        jobTemplateList = jobTemplateDAO.getAll();

        table = dataTableGenerator.getStartTable();
        String dataArray[] = new String[5];
        dataArray[0] = "Client";
        dataArray[1] = "From";
        dataArray[2] = "To";
        dataArray[3] = "Container Size";
        dataArray[4] = "Job Type";
        table = table + dataTableGenerator.getTableHeader(dataArray);
        table = table + dataTableGenerator.getStartTableBody();

        for (JobTemplate jobTemplate : jobTemplateList) {
            dataArray[0] = jobTemplate.getClient().getOrganizationName();
            dataArray[1] = jobTemplate.getFromLocation();
            dataArray[2] = jobTemplate.getToLocation();
            dataArray[3] = jobTemplate.getContainerSize() + "\"";
            dataArray[4] = jobTemplate.getJobType().getDisplayName();
            table = table + dataTableGenerator.getTableBodyRow(dataArray, "edit/" + jobTemplate.getId(), "delete/" + jobTemplate.getId());
        }

        table = table + dataTableGenerator.getEndTableBody();
        table = table + dataTableGenerator.getEndTable();
        return table;
    }

    public JobTemplate getJobTemplate(String jobTemplateId) {
        JobTemplateDAO jobTemplateDAO = new JobTemplateDAO();
        JobTemplate jobTemplate;
        try {
            jobTemplate = jobTemplateDAO.getById(Integer.parseInt(jobTemplateId));
        } catch (Exception e) {
            log.error("[" + className + "] getJobTemplate: error in retrieving JobTemlate by Id");
            return null;
        }
        return jobTemplate;
    }

    public String getJobTemplateSelectList() {
        JobTemplateDAO jobTemplateDAO = new JobTemplateDAO();
        List<JobTemplate> jobTemplateList = jobTemplateDAO.getAll();

        String list = "";
        for (JobTemplate jobTemplate : jobTemplateList) {
            list = list + "\t\t<option value =\"" + jobTemplate.getId() + "\">" + jobTemplate.getDisplayName() + "</option>\n";
        }
        log.debug("[" + className + "] getJobTemplateSelectList()");
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
                    .add("labourCharges", 0)
                    .add("hireCharges", 0)
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

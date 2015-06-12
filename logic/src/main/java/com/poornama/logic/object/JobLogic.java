package com.poornama.logic.object;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.Employee;
import com.poornama.api.objects.Job;
import com.poornama.api.objects.JobTemplate;
import com.poornama.api.objects.Vehicle;
import com.poornama.api.presentation.DataTableGenerator;
import com.poornama.api.presentation.Notification;
import com.poornama.api.presentation.NotificationType;
import com.poornama.data.dao.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by ddhananjaya on 6/11/15.
 */
@Component
public class JobLogic  {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = JobLogic.class.getName();

    public Notification createJob(HttpServletRequest request) {
        JobDAO jobDAO = new JobDAO();
        JobTemplateDAO jobTemplateDAO = new JobTemplateDAO();
        VehicleDAO vehicleDAO = new VehicleDAO();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Job job = new Job();
        Notification notification = new Notification();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy h:mm a");
        Date startDate = new Date();
        Date endDate = new Date();
        Employee driver = new Employee();
        Employee cleaner = new Employee();
        Vehicle vehicle = new Vehicle();
        JobTemplate jobTemplate = new JobTemplate();

        BigDecimal hireCharges = null;
        BigDecimal labourCharges;
        BigDecimal containerCharges;
        BigDecimal detentionCharges;
        BigDecimal hourlyDetentionCharges;
        BigDecimal dailyContainerCharges;

        int freeHours = 0;

        try {
            startDate = simpleDateFormat.parse(request.getParameter("startDate"));
        } catch (ParseException e) {
            log.error("[" + className + "] createJob: Error in parsing startDate");
        }

        try {
            endDate = simpleDateFormat.parse(request.getParameter("endDate"));
        } catch (ParseException e) {
            log.error("[" + className + "] createJob: Error in parsing endDate");
        }

        try {
            driver = employeeDAO.getById(request.getParameter("driver"));
        } catch (Exception e) {
            log.error("[" + className + "] createJob: Error in retrieving driver");
        }

        try {
            cleaner = employeeDAO.getById(request.getParameter("cleaner"));
        } catch (Exception e) {
            log.error("[" + className + "] createJob: Error in retrieving cleaner");
        }

        try {
            vehicle = vehicleDAO.getById(request.getParameter("vehicle"));
        } catch (Exception e) {
            log.error("[" + className + "] createJob: Error in retrieving vehicle");
        }

        try {
            jobTemplate = jobTemplateDAO.getById(request.getParameter("jobTemplate"));
        } catch (Exception e) {
            log.error("[" + className + "] createJob: Error in retrieving jobTemplate");
        }

        try {
            freeHours = Integer.parseInt(request.getParameter("freeHours"));
        } catch (NumberFormatException e) {
            log.error("[" + className + "] createJob: Error in parsing freeHours");
        }

        hireCharges = new BigDecimal(request.getParameter("hireCharges"));
        labourCharges = new BigDecimal(request.getParameter("labourCharges"));
        containerCharges = new BigDecimal(request.getParameter("containerCharges"));
        detentionCharges = new BigDecimal(request.getParameter("detentionCharges"));
        hourlyDetentionCharges = new BigDecimal(request.getParameter("hourlyDetentionCharges"));
        dailyContainerCharges = new BigDecimal(request.getParameter("dailyContainerCharges"));

        job.setJobTemplate(jobTemplate);
        job.setDriver(driver);
        job.setCleaner(cleaner);
        job.setVehicle(vehicle);
        job.setStartDate(startDate);
        job.setEndDate(endDate);
        job.setHireCharges(hireCharges);
        job.setLabourCharges(labourCharges);
        job.setContainerCharges(containerCharges);
        job.setDetentionCharges(detentionCharges);
        job.setHourlyDetentionCharges(hourlyDetentionCharges);
        job.setDailyContainerCharges(dailyContainerCharges);
        job.setFreeHours(freeHours);

        try {
            jobDAO.create(job);
            notification.setInteger(job.getId());
            notification.setNotificationType(NotificationType.SUCCESS);
            notification.setMessage("Job created successfully.");
            log.info("[" + className + "] createJob: created Job");
        } catch (Exception e) {
            notification.setNotificationType(NotificationType.DANGER);
            notification.setMessage("Something went wrong with creating job. Please try again.");
            log.error("[" + className + "] createJob: failed creating job");
        }

        return notification;
    }

    public Notification editJob(HttpServletRequest request, String jobId) {
        JobDAO jobDAO = new JobDAO();
        JobTemplateDAO jobTemplateDAO = new JobTemplateDAO();
        VehicleDAO vehicleDAO = new VehicleDAO();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Job job = new Job();
        Notification notification = new Notification();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy h:mm a");
        Date startDate = new Date();
        Date endDate = new Date();
        Employee driver = new Employee();
        Employee cleaner = new Employee();
        Vehicle vehicle = new Vehicle();
        JobTemplate jobTemplate = new JobTemplate();

        BigDecimal hireCharges = null;
        BigDecimal labourCharges;
        BigDecimal containerCharges;
        BigDecimal detentionCharges;
        BigDecimal hourlyDetentionCharges;
        BigDecimal dailyContainerCharges;

        int id = 0;
        int freeHours = 0;

        try {
            startDate = simpleDateFormat.parse(request.getParameter("startDate"));
        } catch (ParseException e) {
            log.error("[" + className + "] editJob: Error in parsing startDate");
        }

        try {
            endDate = simpleDateFormat.parse(request.getParameter("endDate"));
        } catch (ParseException e) {
            log.error("[" + className + "] editJob: Error in parsing endDate");
        }

        try {
            driver = employeeDAO.getById(request.getParameter("driver"));
        } catch (Exception e) {
            log.error("[" + className + "] editJob: Error in retrieving driver");
        }

        try {
            cleaner = employeeDAO.getById(request.getParameter("cleaner"));
        } catch (Exception e) {
            log.error("[" + className + "] editJob: Error in retrieving cleaner");
        }

        try {
            vehicle = vehicleDAO.getById(request.getParameter("vehicle"));
        } catch (Exception e) {
            log.error("[" + className + "] editJob: Error in retrieving vehicle");
        }

        try {
            jobTemplate = jobTemplateDAO.getById(request.getParameter("jobTemplate"));
        } catch (Exception e) {
            log.error("[" + className + "] editJob: Error in retrieving jobTemplate");
        }

        try {
            freeHours = Integer.parseInt(request.getParameter("freeHours"));
        } catch (NumberFormatException e) {
            log.error("[" + className + "] editJob: Error in parsing freeHours");
        }

        try {
            id = Integer.parseInt(jobId);
        } catch (Exception e) {
            log.error("[" + className + "] editJob: Error in parsing jobId");
        }

        hireCharges = new BigDecimal(request.getParameter("hireCharges"));
        labourCharges = new BigDecimal(request.getParameter("labourCharges"));
        containerCharges = new BigDecimal(request.getParameter("containerCharges"));
        detentionCharges = new BigDecimal(request.getParameter("detentionCharges"));
        hourlyDetentionCharges = new BigDecimal(request.getParameter("hourlyDetentionCharges"));
        dailyContainerCharges = new BigDecimal(request.getParameter("dailyContainerCharges"));

        job = jobDAO.getById(jobId);
        job.setId(id);
        job.setJobTemplate(jobTemplate);
        job.setDriver(driver);
        job.setCleaner(cleaner);
        job.setVehicle(vehicle);
        job.setStartDate(startDate);
        job.setEndDate(endDate);
        job.setHireCharges(hireCharges);
        job.setLabourCharges(labourCharges);
        job.setContainerCharges(containerCharges);
        job.setDetentionCharges(detentionCharges);
        job.setHourlyDetentionCharges(hourlyDetentionCharges);
        job.setDailyContainerCharges(dailyContainerCharges);
        job.setFreeHours(freeHours);

        try {
            jobDAO.update(job);
            notification.setNotificationType(NotificationType.SUCCESS);
            notification.setMessage("Job saved successfully.");
            log.info("[" + className + "] editJob: created Job");
        } catch (Exception e) {
            notification.setNotificationType(NotificationType.DANGER);
            notification.setMessage("Something went wrong with saving job. Please try again.");
            log.error("[" + className + "] editJob: failed creating job");
        }

        return notification;
    }

    public Notification deleteJob(String jobId) {
        Notification notification = new Notification();
        JobDAO jobDAO = new JobDAO();

        try {
            Job job = jobDAO.getById(jobId);
            jobDAO.delete(job);
            notification.setNotificationType(NotificationType.SUCCESS);
            notification.setMessage("Deleted job successfully");
        } catch (Exception e) {
            notification.setNotificationType(NotificationType.DANGER);
            notification.setMessage("Deleted job failed. Please try again.");
        }

        return notification;
    }

    public Job getJob(String jobId){
        JobDAO jobDAO = new JobDAO();
        Job job;
        try {
            job = jobDAO.getById(Integer.parseInt(jobId));
        } catch (Exception e) {
            log.error("[" + className + "] getJob: error in retrieving Job by Id");
            return null;
        }
        return job;
    }


    public String getJobTable(String searchCriteria) {
        List<Job> jobList;
        JobDAO jobDAO = new JobDAO();
        DataTableGenerator dataTableGenerator = new DataTableGenerator();
        String table;

        if (searchCriteria.equals("")) {
            jobList = jobDAO.getAll();
        } else {
            jobList = jobDAO.searchById(searchCriteria);
        }

        table = dataTableGenerator.getStartTable();
        String dataArray[] = new String[7];
        dataArray[0] = "Job No.";
        dataArray[1] = "From";
        dataArray[2] = "To";
        dataArray[3] = "Vehicle";
        dataArray[4] = "Driver";
        dataArray[5] = "Cleaner";
        dataArray[6] = "Total";
        table = table + dataTableGenerator.getTableHeader(dataArray);
        table = table + dataTableGenerator.getStartTableBody();

        for (Job job : jobList) {
            BigDecimal totalCharge = new BigDecimal("0");
            totalCharge = totalCharge.add(job.getHireCharges());
            totalCharge = totalCharge.add(job.getContainerCharges());
            totalCharge = totalCharge.add(job.getDetentionCharges());
            totalCharge = totalCharge.add(job.getLabourCharges());

            dataArray[0] = String.valueOf(job.getId());
            dataArray[1] = job.getJobTemplate().getFromLocation();
            dataArray[2] = job.getJobTemplate().getToLocation();
            dataArray[3] = job.getVehicle().getVehicleNumber();
            dataArray[4] = job.getDriver().getFirstName();
            dataArray[5] = job.getCleaner().getFirstName();
            dataArray[6] = totalCharge.toString();
            table = table + dataTableGenerator.getTableBodyRow(dataArray, "edit/" + job.getId(), "delete/" + job.getId());
        }

        table = table + dataTableGenerator.getEndTableBody();
        table = table + dataTableGenerator.getEndTable();
        return table;
    }

}

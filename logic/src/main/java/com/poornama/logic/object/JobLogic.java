package com.poornama.logic.object;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.Employee;
import com.poornama.api.objects.Job;
import com.poornama.api.objects.JobTemplate;
import com.poornama.api.objects.Vehicle;
import com.poornama.api.presentation.*;
import com.poornama.data.dao.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by ddhananjaya on 6/11/15.
 */
@Service
public class JobLogic {
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

        BigDecimal hireCharges;
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
        Job job;
        Notification notification = new Notification();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy h:mm a");
        Date startDate = new Date();
        Date endDate = new Date();
        Employee driver = new Employee();
        Employee cleaner = new Employee();
        Vehicle vehicle = new Vehicle();
        JobTemplate jobTemplate = new JobTemplate();

        BigDecimal hireCharges;
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

    public Job getJob(String jobId) {
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


    public String getJobTable(String jobId) {
        List<Job> jobList;
        JobDAO jobDAO = new JobDAO();
        PrintDataTableGenerator printDataTableGenerator = new PrintDataTableGenerator();
        String table;

        table = printDataTableGenerator.getStartTable();
        String dataArray[] = new String[7];
        dataArray[0] = "Job No.";
        dataArray[1] = "From";
        dataArray[2] = "To";
        dataArray[3] = "Vehicle";
        dataArray[4] = "Driver";
        dataArray[5] = "Cleaner";
        dataArray[6] = "Total";
        table = table + printDataTableGenerator.getTableHeader(dataArray);
        table = table + printDataTableGenerator.getStartTableBody();
        if (jobId.equals("")) {
            jobList = jobDAO.getAll();
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
                table = table + printDataTableGenerator.getTableBodyRow(dataArray, "print/" + job.getId(), "edit/" + job.getId(), "delete/" + job.getId());
            }
        } else {
            Job job = jobDAO.getById(jobId);
            if (job != null) {
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
                table = table + printDataTableGenerator.getTableBodyRow(dataArray, "print/" + job.getId(), "edit/" + job.getId(), "delete/" + job.getId());
            }
        }

        table = table + printDataTableGenerator.getEndTableBody();
        table = table + printDataTableGenerator.getEndTable();
        return table;
    }

    public String getInvoiceTable(String jobId) {
        JobDAO jobDAO = new JobDAO();
        Job job = jobDAO.getById(jobId);
        JobTemplate jobTemplate = job.getJobTemplate();
        PlainDataTableGenerator plainDataTableGenerator = new PlainDataTableGenerator();
        plainDataTableGenerator.setTableType("table-bordered");
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy h:mm a");

        String table;
        long totalSeconds = (job.getEndDate().getTime() - job.getStartDate().getTime()) / 1000;
        int totalHours = (int) totalSeconds / 3600;
        int detentionHours = 0;

        if (totalHours >= job.getFreeHours()) {
            detentionHours = totalHours - job.getFreeHours();
        }

        BigDecimal totalCharge = new BigDecimal("0");
        totalCharge = totalCharge.add(job.getHireCharges());
        totalCharge = totalCharge.add(job.getContainerCharges());
        totalCharge = totalCharge.add(job.getDetentionCharges());
        totalCharge = totalCharge.add(job.getLabourCharges());

        table = plainDataTableGenerator.getStartTable();

        String dataArray[] = new String[3];
        dataArray[0] = "Description";
        dataArray[1] = "";
        dataArray[2] = "Amount";

        table = table + plainDataTableGenerator.getTableHeader(dataArray);
        table = table + plainDataTableGenerator.getStartTableBody();

        dataArray[0] = "From";
        dataArray[1] = jobTemplate.getFromLocation();
        dataArray[2] = "";
        table = table + plainDataTableGenerator.getTableBodyRow(dataArray);

        dataArray[0] = "To";
        dataArray[1] = jobTemplate.getToLocation();
        dataArray[2] = "";
        table = table + plainDataTableGenerator.getTableBodyRow(dataArray);

        dataArray[0] = "Type";
        dataArray[1] = jobTemplate.getJobType().getDisplayName();
        dataArray[2] = "";
        table = table + plainDataTableGenerator.getTableBodyRow(dataArray);

        dataArray[0] = "Container Size";
        dataArray[1] = Integer.toString(jobTemplate.getContainerSize()) + " ft.";
        dataArray[2] = "";
        table = table + plainDataTableGenerator.getTableBodyRow(dataArray);

        dataArray[0] = "Transport Charge";
        dataArray[1] = "";
        dataArray[2] = "Rs. " + job.getHireCharges().toString();
        table = table + plainDataTableGenerator.getTableBodyRow(dataArray);

        dataArray[0] = "Start time";
        dataArray[1] = dateFormat.format(job.getStartDate());
        dataArray[2] = "";
        table = table + plainDataTableGenerator.getTableBodyRow(dataArray);

        dataArray[0] = "End time";
        dataArray[1] = dateFormat.format(job.getEndDate());
        dataArray[2] = "";
        table = table + plainDataTableGenerator.getTableBodyRow(dataArray);

        dataArray[0] = "Total hours";
        dataArray[1] = Integer.toString(totalHours);
        dataArray[2] = "";
        table = table + plainDataTableGenerator.getTableBodyRow(dataArray);

        dataArray[0] = "Free hours";
        dataArray[1] = Integer.toString(job.getFreeHours());
        dataArray[2] = "";
        table = table + plainDataTableGenerator.getTableBodyRow(dataArray);

        dataArray[0] = "Detention hours";
        dataArray[1] = Integer.toString(detentionHours);
        dataArray[2] = "";
        table = table + plainDataTableGenerator.getTableBodyRow(dataArray);

        dataArray[0] = "Detention Charge";
        dataArray[1] = "( " + Integer.toString(detentionHours) + " x Rs. " + jobTemplate.getHourlyDetentionCharges() + " )";
        dataArray[2] = "Rs. " + job.getDetentionCharges().toString();
        table = table + plainDataTableGenerator.getTableBodyRow(dataArray);

        dataArray[0] = "Container Charge";
        dataArray[1] = "( " + Integer.toString(totalHours / 24) + " x Rs. " + jobTemplate.getDailyContainerCharges() + " )";
        dataArray[2] = "Rs. " + job.getContainerCharges().toString();
        table = table + plainDataTableGenerator.getTableBodyRow(dataArray);

        dataArray[0] = "Labour Charge";
        dataArray[1] = "";
        dataArray[2] = "Rs. " + job.getLabourCharges().toString();
        table = table + plainDataTableGenerator.getTableBodyRow(dataArray);

        dataArray[0] = "<b>Total Amount</b>";
        dataArray[1] = "";
        dataArray[2] = "<b>Rs. " + totalCharge.toString() + "</b>";
        table = table + plainDataTableGenerator.getTableBodyRow(dataArray);

        table = table + plainDataTableGenerator.getEndTableBody();
        table = table + plainDataTableGenerator.getEndTable();
        return table;
    }

}

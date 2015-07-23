package com.poornama.logic.object;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.*;
import com.poornama.data.dao.*;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ddhananjaya on 7/14/15.
 */
public class SalaryLogic {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = SalaryLogic.class.getName();

    public void calculateSalary(Date date) {
        int driverCommission = 0;
        int cleanerCommission = 0;

        int driverBasicSalary = 0;
        int cleanerBasicSalary = 0;

        ConfigurationDAO configurationDAO = new ConfigurationDAO();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        EmployeeAttendanceDAO employeeAttendanceDAO = new EmployeeAttendanceDAO();
        JobDAO jobDAO = new JobDAO();
        SalaryDAO salaryDAO = new SalaryDAO();

        Configuration driverCommissionConfiguration = configurationDAO.getByName("driverCommission");
        Configuration cleanerCommissionConfiguration = configurationDAO.getByName("cleanerCommission");
        Configuration driverBasicSalaryConfiguration = configurationDAO.getByName("driverBasicSalary");
        Configuration cleanerBasicSalaryConfiguration = configurationDAO.getByName("cleanerBasicSalary");

        try {
            driverCommission = Integer.parseInt(driverCommissionConfiguration.getValue());
            cleanerCommission = Integer.parseInt(cleanerCommissionConfiguration.getValue());
            driverBasicSalary = Integer.parseInt(driverBasicSalaryConfiguration.getValue());
            cleanerBasicSalary = Integer.parseInt(cleanerBasicSalaryConfiguration.getValue());
        } catch (NumberFormatException e) {
            log.error("[" + className + "] calculateSalary : error in configuration driver/cleaner commission/salary");
        }

        List<Employee> employeeList = employeeDAO.getAll();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
        Date firstDay = calendar.getTime();

        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        Date lastDay = calendar.getTime();

        for (Employee employee : employeeList) {
            int attendedDays = 0;
            int totalHireAmount = 0;
            int basicSalary = 0;
            int commission = 0;
            int labourCharges = 0;
            int workingDays = 0;

            if (!employee.getEmployeeType().getName().equals("driver") || !employee.getEmployeeType().getName().equals("driver")) {
                continue;
            }

            Date tempDate = firstDay;

            Calendar tempCalendar = Calendar.getInstance();

            while (tempDate.compareTo(lastDay) != 0) {

                EmployeeAttendance employeeAttendance = employeeAttendanceDAO.getByIdDate(employee, tempDate);

                if (employeeAttendance.isAttendance()) {
                    attendedDays++;
                }

                if (tempCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && tempCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                    workingDays++;
                }

                List<Job> jobList = null;

                if (employee.getEmployeeType().getName().equals("driver") ) {
                    jobList = jobDAO.getByDriverDate(employee, tempDate);
                }

                if (employee.getEmployeeType().getName().equals("cleaner") ) {
                    jobList = jobDAO.getByCleanerDate(employee, tempDate);
                }

                for (Job job : jobList) {
                    totalHireAmount = totalHireAmount + Integer.valueOf(job.getHireCharges().intValue());
                    labourCharges = labourCharges + (Integer.valueOf(job.getLabourCharges().intValue()) / 2);
                }

                tempCalendar.setTime(tempDate);
                tempCalendar.add(Calendar.DATE, 1);
                tempDate = tempCalendar.getTime();
            }

            if (employee.getEmployeeType().getName().equals("driver") ) {
                basicSalary = driverBasicSalary / workingDays * attendedDays;
                commission = totalHireAmount * driverCommission;
            }

            if (employee.getEmployeeType().getName().equals("cleaner") ) {
                basicSalary = cleanerBasicSalary / workingDays * attendedDays;
                commission = totalHireAmount * cleanerCommission;
            }

            Salary salary = new Salary();

            salary.setEmployee(employee);
            salary.setBasicComponent(new BigDecimal(basicSalary));
            salary.setCommissionComponent(new BigDecimal(commission));
            salary.setOtherAllowances(new BigDecimal(labourCharges));

            salaryDAO.create(salary);
        }
    }
}

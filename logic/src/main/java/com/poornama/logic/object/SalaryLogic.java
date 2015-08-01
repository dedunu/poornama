package com.poornama.logic.object;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.Configuration;
import com.poornama.api.objects.Employee;
import com.poornama.api.objects.EmployeeAttendance;
import com.poornama.api.objects.Job;
import com.poornama.api.objects.Salary;
import com.poornama.api.presentation.PlainDataTableGenerator;
import com.poornama.data.dao.ConfigurationDAO;
import com.poornama.data.dao.EmployeeAttendanceDAO;
import com.poornama.data.dao.EmployeeDAO;
import com.poornama.data.dao.JobDAO;
import com.poornama.data.dao.SalaryDAO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ddhananjaya on 7/14/15.
 */
@Service
public class SalaryLogic {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = SalaryLogic.class.getName();

    public void calculateSalary(HttpServletRequest request) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yyyy");
        Date date = new Date();

        try {
            date = simpleDateFormat.parse(request.getParameter("salaryMonth"));
        } catch (ParseException e) {
            log.error("[" + className + "] calculateSalary: Error in parsing salaryMonth");
        }

        calculateSalary(date);
    }

    public void calculateSalary(Date date) {
        double driverCommission = 0;
        double cleanerCommission = 0;

        double driverBasicSalary = 0;
        double cleanerBasicSalary = 0;
        double managerBasicSalary = 0;
        double technicianBasicSalary = 0;

        double employeeEPF = 0;

        ConfigurationDAO configurationDAO = new ConfigurationDAO();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        EmployeeAttendanceDAO employeeAttendanceDAO = new EmployeeAttendanceDAO();
        JobDAO jobDAO = new JobDAO();
        SalaryDAO salaryDAO = new SalaryDAO();

        Configuration employeeEPFConfiguration = configurationDAO.getByName("employeeEPF");
        Configuration driverCommissionConfiguration = configurationDAO.getByName("driverCommission");
        Configuration cleanerCommissionConfiguration = configurationDAO.getByName("cleanerCommission");
        Configuration driverBasicSalaryConfiguration = configurationDAO.getByName("driverBasicSalary");
        Configuration cleanerBasicSalaryConfiguration = configurationDAO.getByName("cleanerBasicSalary");
        Configuration managerBasicSalaryConfiguration = configurationDAO.getByName("managerBasicSalary");
        Configuration technicianBasicSalaryConfiguration = configurationDAO.getByName("technicianBasicSalary");

        try {
            driverCommission = Double.parseDouble(driverCommissionConfiguration.getValue());
            cleanerCommission = Double.parseDouble(cleanerCommissionConfiguration.getValue());
            driverBasicSalary = Double.parseDouble(driverBasicSalaryConfiguration.getValue());
            cleanerBasicSalary = Double.parseDouble(cleanerBasicSalaryConfiguration.getValue());
            managerBasicSalary = Double.parseDouble(managerBasicSalaryConfiguration.getValue());
            technicianBasicSalary = Double.parseDouble(technicianBasicSalaryConfiguration.getValue());
            employeeEPF = Double.parseDouble(employeeEPFConfiguration.getValue());
        } catch (NumberFormatException e) {
            log.error("[" + className + "] calculateSalary : error in configuration commission/salary");
        }

        List<Employee> employeeList = employeeDAO.getAll();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
        Date firstDay = calendar.getTime();

        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        Date lastDay = calendar.getTime();

        for (Employee employee : employeeList) {
            double attendedDays = 0;
            double totalHireAmount = 0;
            double basicSalary = 0;
            double commission = 0;
            double labourCharges = 0;
            double workingDays = 0;

            Date tempDate = firstDay;

            Calendar tempCalendar = Calendar.getInstance();

            while (tempDate.compareTo(lastDay) != 0) {

                EmployeeAttendance employeeAttendance = employeeAttendanceDAO.getByIdDate(employee, tempDate);

                if (employeeAttendance != null && employeeAttendance.isAttendance()) {
                    attendedDays++;
                }

                if (tempCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY && tempCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                    workingDays++;
                }

                tempCalendar.setTime(tempDate);
                tempCalendar.add(Calendar.DATE, 1);
                tempDate = tempCalendar.getTime();
            }

            List<Job> jobList = null;

            if (employee.getEmployeeType().getName().equals("driver")) {
                jobList = jobDAO.getByDriverDate(employee, firstDay, lastDay);
            }

            if (employee.getEmployeeType().getName().equals("cleaner")) {
                jobList = jobDAO.getByCleanerDate(employee, firstDay, lastDay);
            }

            if (jobList != null) {
                for (Job job : jobList) {
                    totalHireAmount = totalHireAmount + job.getHireCharges().doubleValue();
                    labourCharges = labourCharges + (job.getLabourCharges().doubleValue() / 2);
                }
            }

            if (employee.getEmployeeType().getName().equals("driver")) {
                basicSalary = driverBasicSalary / workingDays * attendedDays;
                commission = totalHireAmount * driverCommission;
            }

            if (employee.getEmployeeType().getName().equals("cleaner")) {
                basicSalary = cleanerBasicSalary / workingDays * attendedDays;
                commission = totalHireAmount * cleanerCommission;
            }

            if (employee.getEmployeeType().getName().equals("manager")) {
                basicSalary = managerBasicSalary / workingDays * attendedDays;
            }

            if (employee.getEmployeeType().getName().equals("technician")) {
                basicSalary = technicianBasicSalary / workingDays * attendedDays;
            }

            BigDecimal employeeEPFAmount = new BigDecimal(basicSalary * employeeEPF);

            BigDecimal netSalary = new BigDecimal(basicSalary);
            netSalary = netSalary.add(new BigDecimal(commission));
            netSalary = netSalary.add(new BigDecimal(labourCharges));

            BigDecimal grossSalary = netSalary.subtract(employeeEPFAmount);

            Salary salary = salaryDAO.getByEmployeeDate(employee, firstDay);

            if (salary == null) {
                salary = new Salary();
            }

            salary.setDate(firstDay);
            salary.setEmployee(employee);
            salary.setBasicComponent(new BigDecimal(basicSalary));
            salary.setCommissionComponent(new BigDecimal(commission));
            salary.setOtherAllowances(new BigDecimal(labourCharges));
            salary.setNetSalary(netSalary);
            salary.setGrossSalary(grossSalary);

            salaryDAO.saveOrUpdate(salary);
        }
    }

    public String getSalaryTable(HttpServletRequest request) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/yyyy");
        Date date = new Date();

        try {
            date = simpleDateFormat.parse(request.getParameter("salaryMonth"));
        } catch (ParseException e) {
            log.error("[" + className + "] getSalaryTable: Error in parsing salaryMonth");
        }

        return getSalaryTable(date);
    }

    public String getSalaryTable(Date date) {
        ConfigurationDAO configurationDAO = new ConfigurationDAO();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        SalaryDAO salaryDAO = new SalaryDAO();

        double employeeEPF = 0;
        double companyEPF = 0;
        double companyETF = 0;

        String table;
        String dataArray[] = new String[10];
        PlainDataTableGenerator plainDataTableGenerator = new PlainDataTableGenerator();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
        Date firstDay = calendar.getTime();

        Configuration employeeEPFConfiguration = configurationDAO.getByName("employeeEPF");
        Configuration companyEPFConfiguration = configurationDAO.getByName("companyEPF");
        Configuration companyETFConfiguration = configurationDAO.getByName("companyETF");

        try {
            employeeEPF = Double.parseDouble(employeeEPFConfiguration.getValue());
            companyEPF = Double.parseDouble(companyEPFConfiguration.getValue());
            companyETF = Double.parseDouble(companyETFConfiguration.getValue());
        } catch (NumberFormatException e) {
            log.error("[" + className + "] getSalaryTable : error in configuration");
        }

        List<Employee> employeeList = employeeDAO.getAll();

        table = plainDataTableGenerator.getStartTable();

        dataArray[0] = "Employee ID";
        dataArray[1] = "Name";
        dataArray[2] = "Basic Salary";
        dataArray[3] = "Commission";
        dataArray[4] = "Other Allowances";
        dataArray[5] = "Employee EPF";
        dataArray[6] = "Company EPF";
        dataArray[7] = "Company ETF";
        dataArray[8] = "Net Salary";
        dataArray[9] = "Balance Amount";

        table = table + plainDataTableGenerator.getTableHeader(dataArray);
        table = table + plainDataTableGenerator.getStartTableBody();

        for (Employee employee : employeeList) {
            Salary salary = salaryDAO.getByEmployeeDate(employee, firstDay);

            BigDecimal employeeEPFAmount = new BigDecimal(salary.getBasicComponent().doubleValue() * employeeEPF);
            BigDecimal companyEPFAmount = new BigDecimal(salary.getBasicComponent().doubleValue() * companyEPF);
            BigDecimal companyETFAmount = new BigDecimal(salary.getBasicComponent().doubleValue() * companyETF);

            BigDecimal netSalary = salary.getBasicComponent();
            netSalary = netSalary.add(salary.getCommissionComponent());
            netSalary = netSalary.add(salary.getOtherAllowances());

            BigDecimal balanceAmount = netSalary.subtract(employeeEPFAmount);

            employeeEPFAmount = employeeEPFAmount.setScale(2, BigDecimal.ROUND_HALF_EVEN);
            companyEPFAmount = companyEPFAmount.setScale(2, BigDecimal.ROUND_HALF_EVEN);
            companyETFAmount = companyETFAmount.setScale(2, BigDecimal.ROUND_HALF_EVEN);
            balanceAmount = balanceAmount.setScale(2, BigDecimal.ROUND_HALF_EVEN);

            dataArray[0] = Integer.toString(employee.getId());
            dataArray[1] = employee.getFirstName() + " " + employee.getLastName();
            dataArray[2] = salary.getBasicComponent().toString();
            dataArray[3] = salary.getCommissionComponent().toString();
            dataArray[4] = salary.getOtherAllowances().toString();
            dataArray[5] = employeeEPFAmount.toString();
            dataArray[6] = companyEPFAmount.toString();
            dataArray[7] = companyETFAmount.toString();
            dataArray[8] = netSalary.toString();
            dataArray[9] = balanceAmount.toString();

            table = table + plainDataTableGenerator.getTableBodyRow(dataArray);
        }

        table = table + plainDataTableGenerator.getEndTableBody();
        table = table + plainDataTableGenerator.getEndTable();
        return table;
    }
}

package com.poornama.logic.object;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.*;
import com.poornama.api.presentation.PlainDataTableGenerator;
import com.poornama.data.dao.*;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.DoubleSummaryStatistics;
import java.util.List;

/**
 * Created by ddhananjaya on 7/14/15.
 */
public class SalaryLogic {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = SalaryLogic.class.getName();

    public void calculateSalary(Date date) {
        double driverCommission = 0;
        double cleanerCommission = 0;

        double driverBasicSalary = 0;
        double cleanerBasicSalary = 0;
        double managerBasicSalary = 0;
        double technicianBasicSalary = 0;

        ConfigurationDAO configurationDAO = new ConfigurationDAO();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        EmployeeAttendanceDAO employeeAttendanceDAO = new EmployeeAttendanceDAO();
        JobDAO jobDAO = new JobDAO();
        SalaryDAO salaryDAO = new SalaryDAO();

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
                    totalHireAmount = totalHireAmount + job.getHireCharges().doubleValue();
                    labourCharges = labourCharges + (job.getLabourCharges().doubleValue() / 2);
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

            if (employee.getEmployeeType().getName().equals("manager") ) {
                basicSalary = managerBasicSalary / workingDays * attendedDays;
            }

            if (employee.getEmployeeType().getName().equals("technician") ) {
                basicSalary = technicianBasicSalary / workingDays * attendedDays;
            }

            Salary salary = new Salary();

            salary.setDate(firstDay);
            salary.setEmployee(employee);
            salary.setBasicComponent(new BigDecimal(basicSalary));
            salary.setCommissionComponent(new BigDecimal(commission));
            salary.setOtherAllowances(new BigDecimal(labourCharges));

            salaryDAO.create(salary);
        }
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
            netSalary = netSalary.add(salary.getBasicComponent());
            netSalary = netSalary.add(salary.getCommissionComponent());
            netSalary = netSalary.add(salary.getOtherAllowances());

            BigDecimal balanceAmount = netSalary.subtract(employeeEPFAmount);

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

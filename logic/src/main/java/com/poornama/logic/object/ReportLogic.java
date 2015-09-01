package com.poornama.logic.object;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.Client;
import com.poornama.api.objects.Employee;
import com.poornama.api.objects.Tag;
import com.poornama.api.objects.Vehicle;
import com.poornama.api.reporting.DateHelper;
import com.poornama.api.reporting.DoubleTableHelper;
import com.poornama.api.reporting.IntegerTableHelper;
import com.poornama.data.dao.ClientDAO;
import com.poornama.data.dao.EmployeeDAO;
import com.poornama.data.dao.ReportDAO;
import com.poornama.data.dao.TagDAO;
import com.poornama.data.dao.VehicleDAO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dedunu on 7/29/15.
 */
@Service
public class ReportLogic {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = ReportLogic.class.getName();

    private int EMPLOYEE_TYPE = 1;
    private int CLIENT_TYPE = 2;
    private int VEHICLE_TYPE = 3;
    private int TAG_TYPE = 4;

    private HashMap<Integer, HashMap<String, Integer>> integerTable = new HashMap<Integer, HashMap<String, Integer>>();

    private HashMap<Integer, HashMap<String, Double>> doubleTable = new HashMap<Integer, HashMap<String, Double>>();

    public HashMap<Integer, HashMap<String, Integer>> getIntegerTable() {
        return integerTable;
    }

    public void setIntegerTable(HashMap<Integer, HashMap<String, Integer>> integerTable) {
        this.integerTable = integerTable;
    }

    public HashMap<Integer, HashMap<String, Double>> getDoubleTable() {
        return doubleTable;
    }

    public void setDoubleTable(HashMap<Integer, HashMap<String, Double>> doubleTable) {
        this.doubleTable = doubleTable;
    }

    private List<String> getAxisList(Date startDate, Date endDate, int calendarField) {
        log.debug("[" + className + "] getAxisList() : started");
        List<String> stringList = new ArrayList<String>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date tempDate = startDate;
        Calendar tempCalendar = Calendar.getInstance();
        tempCalendar.setTime(tempDate);

        while (tempDate.before(endDate)) {
            stringList.add(simpleDateFormat.format(tempCalendar.getTime()));
            tempCalendar.add(calendarField, 1);
            tempDate = tempCalendar.getTime();
        }

        return stringList;
    }

    public void generateReport(int reportId, int calendarField, String startDateString, String endDateString) {
        log.debug("[" + className + "] generateReport() : started");
        DateHelper dateHelper = new DateHelper();
        Date startMonthlyDate = dateHelper.getStartDateMonthly(dateHelper.getDate(startDateString));
        Date startAnnuallyDate = dateHelper.getStartDateAnnually(dateHelper.getDate(startDateString));
        Date endMonthlyDate = dateHelper.getEndDateMonthly(dateHelper.getDate(endDateString));
        Date endAnnuallyDate = dateHelper.getEndDateAnnually(dateHelper.getDate(endDateString));
        ReportDAO reportDAO = new ReportDAO();

        switch (reportId) {
            case 1:
                if (calendarField == Calendar.MONTH) {
                    setIntegerTable(reportDAO.getMonthlyEmployeeAttendanceReport(startMonthlyDate, endMonthlyDate));
                }
                if (calendarField == Calendar.YEAR) {
                    setIntegerTable(reportDAO.getAnnualEmployeeAttendanceReport(startAnnuallyDate, endAnnuallyDate));
                }
                break;
            case 2:
                if (calendarField == Calendar.MONTH) {
                    setDoubleTable(reportDAO.getMonthlyEmployeeSalaryReport(startMonthlyDate, endMonthlyDate));
                }
                if (calendarField == Calendar.YEAR) {
                    setDoubleTable(reportDAO.getAnnualEmployeeSalaryReport(startAnnuallyDate, endAnnuallyDate));
                }
                break;
            case 3:
                if (calendarField == Calendar.MONTH) {
                    setDoubleTable(reportDAO.getMonthlyEmployeeRevenueReport(startMonthlyDate, endMonthlyDate));
                }
                if (calendarField == Calendar.YEAR) {
                    setDoubleTable(reportDAO.getAnnualEmployeeRevenueReport(startAnnuallyDate, endAnnuallyDate));
                }
                break;
            case 4:
                if (calendarField == Calendar.MONTH) {
                    setDoubleTable(reportDAO.getMonthlyClientRevenueReport(startMonthlyDate, endMonthlyDate));
                }
                if (calendarField == Calendar.YEAR) {
                    setDoubleTable(reportDAO.getAnnualClientRevenueReport(startAnnuallyDate, endAnnuallyDate));
                }
                break;
            case 5:
                if (calendarField == Calendar.MONTH) {
                    setDoubleTable(reportDAO.getMonthlyVehicleRevenueReport(startMonthlyDate, endMonthlyDate));
                }
                if (calendarField == Calendar.YEAR) {
                    setDoubleTable(reportDAO.getAnnualVehicleRevenueReport(startAnnuallyDate, endAnnuallyDate));
                }
                break;
            case 6:
                if (calendarField == Calendar.MONTH) {
                    setDoubleTable(reportDAO.getMonthlyVehicleMilageReport(startMonthlyDate, endMonthlyDate));
                }
                if (calendarField == Calendar.YEAR) {
                    setDoubleTable(reportDAO.getAnnualVehicleMileageReport(startAnnuallyDate, endAnnuallyDate));
                }
                break;
            case 7:
                if (calendarField == Calendar.MONTH) {
                    setDoubleTable(reportDAO.getMonthlyExpenseReport(startMonthlyDate, endMonthlyDate));
                }
                if (calendarField == Calendar.YEAR) {
                    setDoubleTable(reportDAO.getAnnualExpenseReport(startAnnuallyDate, endAnnuallyDate));
                }
                break;
            default:
                break;
        }

    }

    public String getChartString(int reportId, int calendarField, String startDateString, String endDateString) {
        log.debug("[" + className + "] getChartString() : started");
        DateHelper dateHelper = new DateHelper();
        Date startMonthlyDate = dateHelper.getStartDateMonthly(dateHelper.getDate(startDateString));
        Date startAnnuallyDate = dateHelper.getStartDateAnnually(dateHelper.getDate(startDateString));
        Date endMonthlyDate = dateHelper.getEndDateMonthly(dateHelper.getDate(endDateString));
        Date endAnnuallyDate = dateHelper.getEndDateAnnually(dateHelper.getDate(endDateString));
        DoubleTableHelper doubleTableHelper = new DoubleTableHelper();
        IntegerTableHelper integerTableHelper = new IntegerTableHelper();

        switch (reportId) {
            case 1:
                if (calendarField == Calendar.MONTH) {
                    return integerTableHelper.getChartColumns(getIntegerTable(), startMonthlyDate, endMonthlyDate, getLabels(EMPLOYEE_TYPE), Calendar.MONTH);
                }
                if (calendarField == Calendar.YEAR) {
                    return integerTableHelper.getChartColumns(getIntegerTable(), startAnnuallyDate, endAnnuallyDate, getLabels(EMPLOYEE_TYPE), Calendar.YEAR);
                }
                return null;
            case 2:
                if (calendarField == Calendar.MONTH) {
                    return doubleTableHelper.getChartColumns(getDoubleTable(), startMonthlyDate, endMonthlyDate, getLabels(EMPLOYEE_TYPE), Calendar.MONTH);
                }
                if (calendarField == Calendar.YEAR) {
                    return doubleTableHelper.getChartColumns(getDoubleTable(), startAnnuallyDate, endAnnuallyDate, getLabels(EMPLOYEE_TYPE), Calendar.YEAR);
                }
                return null;
            case 3:
                if (calendarField == Calendar.MONTH) {
                    return doubleTableHelper.getChartColumns(getDoubleTable(), startMonthlyDate, endMonthlyDate, getLabels(EMPLOYEE_TYPE), Calendar.MONTH);
                }
                if (calendarField == Calendar.YEAR) {
                    return doubleTableHelper.getChartColumns(getDoubleTable(), startAnnuallyDate, endAnnuallyDate, getLabels(EMPLOYEE_TYPE), Calendar.YEAR);
                }
                return null;
            case 4:
                if (calendarField == Calendar.MONTH) {
                    return doubleTableHelper.getChartColumns(getDoubleTable(), startMonthlyDate, endMonthlyDate, getLabels(CLIENT_TYPE), Calendar.MONTH);
                }
                if (calendarField == Calendar.YEAR) {
                    return doubleTableHelper.getChartColumns(getDoubleTable(), startAnnuallyDate, endAnnuallyDate, getLabels(CLIENT_TYPE), Calendar.YEAR);
                }
                return null;
            case 5:
                if (calendarField == Calendar.MONTH) {
                    return doubleTableHelper.getChartColumns(getDoubleTable(), startMonthlyDate, endMonthlyDate, getLabels(VEHICLE_TYPE), Calendar.MONTH);
                }
                if (calendarField == Calendar.YEAR) {
                    return doubleTableHelper.getChartColumns(getDoubleTable(), startAnnuallyDate, endAnnuallyDate, getLabels(VEHICLE_TYPE), Calendar.YEAR);
                }
                return null;
            case 6:
                if (calendarField == Calendar.MONTH) {
                    return doubleTableHelper.getChartColumns(getDoubleTable(), startMonthlyDate, endMonthlyDate, getLabels(VEHICLE_TYPE), Calendar.MONTH);
                }
                if (calendarField == Calendar.YEAR) {
                    return doubleTableHelper.getChartColumns(getDoubleTable(), startAnnuallyDate, endAnnuallyDate, getLabels(VEHICLE_TYPE), Calendar.YEAR);
                }
                return null;
            case 7:
                if (calendarField == Calendar.MONTH) {
                    return doubleTableHelper.getChartColumns(getDoubleTable(), startMonthlyDate, endMonthlyDate, getLabels(TAG_TYPE), Calendar.MONTH);
                }
                if (calendarField == Calendar.YEAR) {
                    return doubleTableHelper.getChartColumns(getDoubleTable(), startAnnuallyDate, endAnnuallyDate, getLabels(TAG_TYPE), Calendar.YEAR);
                }
                return null;
            default:
                return null;
        }

    }

    public String getPieChartString(int reportId, int calendarField, String startDateString, String endDateString) {
        log.debug("[" + className + "] getChartString() : started");
        DateHelper dateHelper = new DateHelper();
        Date startMonthlyDate = dateHelper.getStartDateMonthly(dateHelper.getDate(startDateString));
        Date startAnnuallyDate = dateHelper.getStartDateAnnually(dateHelper.getDate(startDateString));
        Date endMonthlyDate = dateHelper.getEndDateMonthly(dateHelper.getDate(endDateString));
        Date endAnnuallyDate = dateHelper.getEndDateAnnually(dateHelper.getDate(endDateString));
        DoubleTableHelper doubleTableHelper = new DoubleTableHelper();

        switch (reportId) {
            case 4:
                if (calendarField == Calendar.MONTH) {
                    return doubleTableHelper.getPieChartColumns(getDoubleTable(), startMonthlyDate, endMonthlyDate, getLabels(CLIENT_TYPE), Calendar.MONTH);
                }
                if (calendarField == Calendar.YEAR) {
                    return doubleTableHelper.getPieChartColumns(getDoubleTable(), startAnnuallyDate, endAnnuallyDate, getLabels(CLIENT_TYPE), Calendar.YEAR);
                }
                return null;
            case 5:
                if (calendarField == Calendar.MONTH) {
                    return doubleTableHelper.getPieChartColumns(getDoubleTable(), startMonthlyDate, endMonthlyDate, getLabels(VEHICLE_TYPE), Calendar.MONTH);
                }
                if (calendarField == Calendar.YEAR) {
                    return doubleTableHelper.getPieChartColumns(getDoubleTable(), startAnnuallyDate, endAnnuallyDate, getLabels(VEHICLE_TYPE), Calendar.YEAR);
                }
                return null;
            case 7:
                if (calendarField == Calendar.MONTH) {
                    return doubleTableHelper.getPieChartColumns(getDoubleTable(), startMonthlyDate, endMonthlyDate, getLabels(TAG_TYPE), Calendar.MONTH);
                }
                if (calendarField == Calendar.YEAR) {
                    return doubleTableHelper.getPieChartColumns(getDoubleTable(), startAnnuallyDate, endAnnuallyDate, getLabels(TAG_TYPE), Calendar.YEAR);
                }
                return null;
            default:
                return null;
        }

    }

    public String getTableString(int reportId, int calendarField, String startDateString, String endDateString) {
        log.debug("[" + className + "] getTableString() : started");
        DateHelper dateHelper = new DateHelper();
        Date startMonthlyDate = dateHelper.getStartDateMonthly(dateHelper.getDate(startDateString));
        Date startAnnuallyDate = dateHelper.getStartDateAnnually(dateHelper.getDate(startDateString));
        Date endMonthlyDate = dateHelper.getEndDateMonthly(dateHelper.getDate(endDateString));
        Date endAnnuallyDate = dateHelper.getEndDateAnnually(dateHelper.getDate(endDateString));
        DoubleTableHelper doubleTableHelper = new DoubleTableHelper();
        IntegerTableHelper integerTableHelper = new IntegerTableHelper();

        switch (reportId) {
            case 1:
                if (calendarField == Calendar.MONTH) {
                    return integerTableHelper.getTable(getIntegerTable(), getLabels(EMPLOYEE_TYPE), getAxisList(startMonthlyDate, endMonthlyDate, Calendar.MONTH));
                }
                if (calendarField == Calendar.YEAR) {
                    return integerTableHelper.getTable(getIntegerTable(), getLabels(EMPLOYEE_TYPE), getAxisList(startAnnuallyDate, endAnnuallyDate, Calendar.YEAR));
                }
                return null;
            case 2:
                if (calendarField == Calendar.MONTH) {
                    return doubleTableHelper.getTable(getDoubleTable(), getLabels(EMPLOYEE_TYPE), getAxisList(startMonthlyDate, endMonthlyDate, Calendar.MONTH));
                }
                if (calendarField == Calendar.YEAR) {
                    return doubleTableHelper.getTable(getDoubleTable(), getLabels(EMPLOYEE_TYPE), getAxisList(startAnnuallyDate, endAnnuallyDate, Calendar.YEAR));
                }
                return null;
            case 3:
                if (calendarField == Calendar.MONTH) {
                    return doubleTableHelper.getTable(getDoubleTable(), getLabels(EMPLOYEE_TYPE), getAxisList(startMonthlyDate, endMonthlyDate, Calendar.MONTH));
                }
                if (calendarField == Calendar.YEAR) {
                    return doubleTableHelper.getTable(getDoubleTable(), getLabels(EMPLOYEE_TYPE), getAxisList(startAnnuallyDate, endAnnuallyDate, Calendar.YEAR));
                }
                return null;
            case 4:
                if (calendarField == Calendar.MONTH) {
                    return doubleTableHelper.getTable(getDoubleTable(), getLabels(CLIENT_TYPE), getAxisList(startMonthlyDate, endMonthlyDate, Calendar.MONTH));
                }
                if (calendarField == Calendar.YEAR) {
                    return doubleTableHelper.getTable(getDoubleTable(), getLabels(CLIENT_TYPE), getAxisList(startAnnuallyDate, endAnnuallyDate, Calendar.YEAR));
                }
                return null;
            case 5:
                if (calendarField == Calendar.MONTH) {
                    return doubleTableHelper.getTable(getDoubleTable(), getLabels(VEHICLE_TYPE), getAxisList(startMonthlyDate, endMonthlyDate, Calendar.MONTH));
                }
                if (calendarField == Calendar.YEAR) {
                    return doubleTableHelper.getTable(getDoubleTable(), getLabels(VEHICLE_TYPE), getAxisList(startAnnuallyDate, endAnnuallyDate, Calendar.YEAR));
                }
                return null;
            case 6:
                if (calendarField == Calendar.MONTH) {
                    return doubleTableHelper.getTable(getDoubleTable(), getLabels(VEHICLE_TYPE), getAxisList(startMonthlyDate, endMonthlyDate, Calendar.MONTH));
                }
                if (calendarField == Calendar.YEAR) {
                    return doubleTableHelper.getTable(getDoubleTable(), getLabels(VEHICLE_TYPE), getAxisList(startAnnuallyDate, endAnnuallyDate, Calendar.YEAR));
                }
                return null;
            case 7:
                if (calendarField == Calendar.MONTH) {
                    return doubleTableHelper.getTable(getDoubleTable(), getLabels(TAG_TYPE), getAxisList(startMonthlyDate, endMonthlyDate, Calendar.MONTH));
                }
                if (calendarField == Calendar.YEAR) {
                    return doubleTableHelper.getTable(getDoubleTable(), getLabels(TAG_TYPE), getAxisList(startAnnuallyDate, endAnnuallyDate, Calendar.YEAR));
                }
                return null;
            default:
                return null;
        }


    }

    public Map<Integer, String> getLabels(int entityNumber) {
        log.debug("[" + className + "] getLabels() : started");
        LinkedHashMap<Integer, String> result = new LinkedHashMap<Integer, String>();
        switch (entityNumber) {
            case 1:
                EmployeeDAO employeeDAO = new EmployeeDAO();
                List<Employee> employeeList = employeeDAO.getAll();
                for (Employee employee : employeeList) {
                    result.put(employee.getId(), employee.getFirstName() + " " + employee.getLastName());
                }
                break;
            case 2:
                ClientDAO clientDAO = new ClientDAO();
                List<Client> clientList = clientDAO.getAll();
                for (Client client : clientList) {
                    result.put(client.getId(), client.getOrganizationName());
                }
                break;
            case 3:
                VehicleDAO vehicleDAO = new VehicleDAO();
                List<Vehicle> vehicleList = vehicleDAO.getAll();
                for (Vehicle vehicle : vehicleList) {
                    result.put(vehicle.getId(), vehicle.getVehicleNumber());
                }
                break;
            case 4:
                TagDAO tagDAO = new TagDAO();
                List<Tag> tagList = tagDAO.getAll();
                for (Tag tag : tagList) {
                    result.put(tag.getId(), tag.getDisplayName());
                }
                break;
            default:
                break;


        }
        log.debug("[" + className + "] getLabels() : finished");
        return result;
    }

}

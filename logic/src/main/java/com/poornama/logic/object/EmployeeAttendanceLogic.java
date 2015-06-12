package com.poornama.logic.object;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.presentation.Notification;
import com.poornama.api.presentation.NotificationType;
import com.poornama.api.presentation.PlainDataTableGenerator;
import com.poornama.data.dao.EmployeeAttendanceDAO;
import com.poornama.data.dao.EmployeeDAO;
import com.poornama.api.objects.Employee;
import com.poornama.api.objects.EmployeeAttendance;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by dedunu on 11/8/14.
 */
@Service
public class EmployeeAttendanceLogic {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = EmployeeAttendanceLogic.class.getName();

    public Notification save(String data, String dateString) {
        Notification notification = new Notification();
        String array[] = data.split("\\|");
        HashMap<String, Integer> attendanceMap = new HashMap<String, Integer>();
        EmployeeDAO employeeDAO = new EmployeeDAO();
        List<Employee> employeeList = employeeDAO.getAll();
        EmployeeAttendanceDAO employeeAttendanceDAO = new EmployeeAttendanceDAO();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date;
        Calendar calendar = Calendar.getInstance();

        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            log.error("[" + className + "] save: Error in parsing startDate");
            notification.setNotificationType(NotificationType.DANGER);
            return notification;
        }

        for (String key : array) {
            attendanceMap.put(key, 1);
        }

        for (Employee employee : employeeList) {
            calendar.setTime(date);
            for (int i = 1; i < 8; i++) {
                EmployeeAttendance employeeAttendance = employeeAttendanceDAO.getByIdDate(employee, calendar.getTime());
                if (employeeAttendance == null) {
                    employeeAttendance = new EmployeeAttendance();
                    employeeAttendance.setEmployee(employee);
                    employeeAttendance.setDate(calendar.getTime());
                }

                if (attendanceMap.get(employee.getId() + "_" + i) == null) {
                    employeeAttendance.setAttendance(false);
                } else {
                    employeeAttendance.setAttendance(true);
                }

                employeeAttendanceDAO.saveOrUpdate(employeeAttendance);
                calendar.add(Calendar.DATE, 1);
            }
        }
        notification.setNotificationType(NotificationType.SUCCESS);
        return notification;
    }

    public String getEmployeeAttendanceTable(String startDate) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        EmployeeAttendanceDAO employeeAttendanceDAO = new EmployeeAttendanceDAO();
        List<Employee> employeeList = employeeDAO.getAll();
        PlainDataTableGenerator plainDataTableGenerator = new PlainDataTableGenerator();
        String table;
        String dataArray[] = new String[8];
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();

        try {
            date = simpleDateFormat.parse(startDate);
        } catch (ParseException e) {
            log.error("[" + className + "] getEmployeeAttendanceTable: Error in parsing startDate");
        }

        calendar.setTime(date);
        dataArray[0] = "Name";
        simpleDateFormat = new SimpleDateFormat("MM/dd");

        for (int i = 1; i < 8; i++) {
            dataArray[i] = simpleDateFormat.format(calendar.getTime());
            calendar.add(Calendar.DATE, 1);
        }

        table = plainDataTableGenerator.getStartTable();
        table = table + plainDataTableGenerator.getTableHeader(dataArray);
        table = table + plainDataTableGenerator.getStartTableBody();

        for (Employee employee : employeeList) {
            dataArray[0] = employee.getFirstName() + " " + employee.getLastName();
            calendar.setTime(date);
            for (int i = 1; i < 8; i++) {
                EmployeeAttendance employeeAttendance = employeeAttendanceDAO.getByIdDate(employee, calendar.getTime());

                if (employeeAttendance == null || !employeeAttendance.isAttendance()) {
                    dataArray[i] = "<input type=\"checkbox\" id=\"attendance\" name=\"attendance\" value=\"" + employee.getId() + "_" + i + "\" />";
                } else {
                    dataArray[i] = "<input type=\"checkbox\" id=\"attendance\" name=\"attendance\" value=\"" + employee.getId() + "_" + i + "\" checked />";
                }

                calendar.add(Calendar.DATE, 1);
            }
            table = table + plainDataTableGenerator.getTableBodyRow(dataArray);
        }

        table = table + plainDataTableGenerator.getEndTableBody();
        table = table + plainDataTableGenerator.getEndTable();
        return table;
    }
}

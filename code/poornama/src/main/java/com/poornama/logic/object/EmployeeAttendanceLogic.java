package com.poornama.logic.object;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.presentation.PlainDataTableGenerator;
import com.poornama.data.dao.EmployeeDAO;
import com.poornama.data.objects.Employee;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by dedunu on 11/8/14.
 */
public class EmployeeAttendanceLogic {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = EmployeeAttendanceLogic.class.getName();

    public String getEmployeeAttendanceTable(String startDate) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
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
        for (int i = 1; i < 8; i++) {
            dataArray[i] = simpleDateFormat.format(calendar.getTime());
            calendar.add(Calendar.DATE, 1);
        }

        table = plainDataTableGenerator.getStartTable();
        table = table + plainDataTableGenerator.getTableHeader(dataArray);
        table = table + plainDataTableGenerator.getStartTableBody();

        for (Employee employee : employeeList) {
            int employeeCount = 0;
            dataArray[0] = employee.getFirstName() + " " + employee.getLastName();
            for (int i = 1; i < 8; i++) {
                dataArray[i] = "<input type=\"checkbox\" name=\"attendance\" value=\"" + employee.getId() + "_" + i + "\"/>";
            }
            employeeCount++;
            table = table + plainDataTableGenerator.getTableBodyRow(dataArray);
        }
        table = table + plainDataTableGenerator.getEndTableBody();
        table = table + plainDataTableGenerator.getEndTable();
        return table;
    }
}

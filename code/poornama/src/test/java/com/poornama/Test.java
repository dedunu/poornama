package com.poornama;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.data.dao.EmployeeAttendanceDAO;
import com.poornama.data.dao.EmployeeDAO;
import com.poornama.data.objects.Employee;
import com.poornama.data.objects.EmployeeAttendance;
import org.apache.log4j.Logger;
import org.junit.Ignore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by dedunu on 11/6/14.
 */
@Ignore
public class Test {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = Test.class.getName();

    public static void main(String args[]) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = simpleDateFormat.parse("05/02/2014");
        EmployeeDAO employeeDAO = new EmployeeDAO();
        EmployeeAttendanceDAO employeeAttendanceDAO = new EmployeeAttendanceDAO();
        List<Employee> employeeList = employeeDAO.searchByFirstName("Dedunu");

        Long l = 3L;
        EmployeeAttendance employeeAttendance1 = employeeAttendanceDAO.getById(l);
        System.out.print(employeeAttendance1.getAttendance());

    }
}

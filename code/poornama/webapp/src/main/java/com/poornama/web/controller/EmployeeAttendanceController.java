package com.poornama.web.controller;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.presentation.Notification;
import com.poornama.api.presentation.NotificationType;
import com.poornama.logic.object.EmployeeAttendanceLogic;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dedunu on 11/8/14.
 */
@Controller
@RequestMapping("/attendance")
public class EmployeeAttendanceController {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = EmployeeAttendanceController.class.getName();

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        log.debug("[" + className + "] index: index()");
        return "employee/attendance/index";
    }

    @RequestMapping(value = "search/{startDate}", method = RequestMethod.POST)
    public void searchAJAX(@PathVariable("startDate") String startDate, HttpServletResponse response) throws IOException {
        startDate = startDate.replace("_", "/");
        EmployeeAttendanceLogic employeeAttendanceLogic = new EmployeeAttendanceLogic();
        String table = employeeAttendanceLogic.getEmployeeAttendanceTable(startDate);
        response.getWriter().print(table);
        log.debug("[" + className + "] searchAJAX()");
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public void saveAJAX(@RequestParam("data") String data, @RequestParam("date") String date, HttpServletResponse response) throws IOException {
        EmployeeAttendanceLogic employeeAttendanceLogic = new EmployeeAttendanceLogic();
        Notification notification = employeeAttendanceLogic.save(data, date);
        if (notification.getNotificationType() == NotificationType.SUCCESS) {
            response.getWriter().print("success");
        } else {
            response.getWriter().print("fail");
        }
        log.debug("[" + className + "] searchAJAX()");
    }
}

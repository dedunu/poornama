package com.poornama.web.controller;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.presentation.Notification;
import com.poornama.api.presentation.NotificationType;
import com.poornama.logic.object.EmployeeAttendanceLogic;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author dedunu
 */
@Controller
@RequestMapping("/attendance")
public class EmployeeAttendanceController {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = EmployeeAttendanceController.class.getName();

    @Autowired
    private EmployeeAttendanceLogic employeeAttendanceLogic;

    @Autowired
    private MessageSource messageSource;

    /**
     * Returns the attendance page
     *
     * @param model Model
     * @return view path as a String
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        log.debug("[" + className + "] index: index()");
        model.addAttribute("pageTitle", messageSource.getMessage("web.attendance.title", null, null));
        return "employee/attendance/index";
    }

    /**
     * Returns employee attendance table
     *
     * @param startDate String
     * @param response  HttpServletResponse
     * @throws IOException
     */
    @RequestMapping(value = "search/{startDate}", method = RequestMethod.POST)
    public void searchAJAX(@PathVariable("startDate") String startDate, HttpServletResponse response) throws IOException {
        startDate = startDate.replace("_", "/");
        String table = employeeAttendanceLogic.getEmployeeAttendanceTable(startDate);
        response.getWriter().print(table);
        log.debug("[" + className + "] searchAJAX()");
    }

    /**
     * Persists attendance
     *
     * @param data     String
     * @param date     String
     * @param response HttpServletResponse
     * @throws IOException
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public void saveAJAX(@RequestParam("data") String data, @RequestParam("date") String date, HttpServletResponse response) throws IOException {
        Notification notification = employeeAttendanceLogic.save(data, date);
        if (notification.getNotificationType() == NotificationType.SUCCESS) {
            response.getWriter().print("success");
        } else {
            response.getWriter().print("fail");
        }
        log.debug("[" + className + "] searchAJAX()");
    }
}

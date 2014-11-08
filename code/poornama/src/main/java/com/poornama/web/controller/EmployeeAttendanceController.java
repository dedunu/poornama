package com.poornama.web.controller;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.logic.object.EmployeeLogic;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dedunu on 11/8/14.
 */
@Controller
@RequestMapping("/employee/attendance")
public class EmployeeAttendanceController {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = EmployeeAttendanceController.class.getName();


    @RequestMapping(value = "search/{startDate}", method = RequestMethod.POST)
    public void searchAJAX(Model model, @PathVariable("startDate") String startDate, HttpServletResponse response) throws IOException {
        EmployeeLogic employeeLogic = new EmployeeLogic();
        String table = employeeLogic.getEmployeeTable(startDate);
        response.getWriter().print(table);
        log.debug("[" + className + "] searchAJAX()");
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public void searchAJAXAll(Model model, HttpServletResponse response) throws IOException {
        searchAJAX(model, "", response);
        log.debug("[" + className + "] searchAJAXAll()");
    }
}

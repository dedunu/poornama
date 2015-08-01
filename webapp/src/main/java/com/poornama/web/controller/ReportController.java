package com.poornama.web.controller;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.logic.object.ReportLogic;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by dedunu on 7/29/15.
 */
@Controller
@RequestMapping("/report/")
public class ReportController {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = ReportController.class.getName();

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model model) throws IOException {
        model.addAttribute("pageTitle", "Poornama Transport Service - Report");
        log.debug("[" + className + "] index()");
        return "report/index";
    }

    @RequestMapping(value = "index", method = RequestMethod.POST)
    public String reportHub(Model model, HttpServletRequest request) throws IOException {
        ReportLogic reportLogic = new ReportLogic();
        String reportId = request.getParameter("report");
        model.addAttribute("pageTitle", "Poornama Transport Service - Report");
        model.addAttribute("report", reportId);
        model.addAttribute("startDate", request.getParameter("startDate"));
        model.addAttribute("endDate", request.getParameter("endDate"));
        model.addAttribute("frequency", request.getParameter("frequency"));

        int frequency = 0;

        try {
            frequency = Integer.parseInt(request.getParameter("frequency"));
        } catch (NumberFormatException e) {

            log.error("[" + className + "] reportHub(): error in parsing reportHub");
        }

        if (reportId.equals("1")) {
            reportLogic.generateReport(1, frequency, request.getParameter("startDate"), request.getParameter("endDate"));
            model.addAttribute("chartText", reportLogic.getChartString(1, frequency, request.getParameter("startDate"), request.getParameter("endDate")));
            model.addAttribute("tableText", reportLogic.getTableString(1, frequency, request.getParameter("startDate"), request.getParameter("endDate")));
            model.addAttribute("displayName", "Employee Attendance Report");

            return "report/result";
        }

        if (reportId.equals("2")) {
            reportLogic.generateReport(2, frequency, request.getParameter("startDate"), request.getParameter("endDate"));
            model.addAttribute("chartText", reportLogic.getChartString(2,frequency, request.getParameter("startDate"), request.getParameter("endDate")));
            model.addAttribute("tableText", reportLogic.getTableString(2,frequency, request.getParameter("startDate"), request.getParameter("endDate")));
            model.addAttribute("displayName", "Employee Salary Report");

            return "report/result";
        }

        if (reportId.equals("3")) {
            reportLogic.generateReport(3, frequency, request.getParameter("startDate"), request.getParameter("endDate"));
            model.addAttribute("chartText", reportLogic.getChartString(3,frequency, request.getParameter("startDate"), request.getParameter("endDate")));
            model.addAttribute("tableText", reportLogic.getTableString(3,frequency, request.getParameter("startDate"), request.getParameter("endDate")));
            model.addAttribute("displayName", "Employee wise Revenue Report");

            return "report/result";
        }

        log.debug("[" + className + "] reportHub()");
        return "report/index";
    }
}

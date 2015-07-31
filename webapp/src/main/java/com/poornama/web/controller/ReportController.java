package com.poornama.web.controller;

import com.poornama.api.logging.GlobalLogger;
import org.apache.log4j.Logger;
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
        String reportId = request.getParameter("report");
        model.addAttribute("pageTitle", "Poornama Transport Service - Report");
        model.addAttribute("report", reportId);
        model.addAttribute("startDate", request.getParameter("startDate"));
        model.addAttribute("endDate", request.getParameter("endDate"));

        if (reportId.equals("1")) {
            model.addAttribute("displayName", "Employee Attendance Report");

            return "report/attendance";
        }

        log.debug("[" + className + "] reportHub()");
        return "report/index";
    }
}

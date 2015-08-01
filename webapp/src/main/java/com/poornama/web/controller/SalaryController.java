package com.poornama.web.controller;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.logic.object.SalaryLogic;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ddhananjaya on 7/15/15.
 */
@Controller
@RequestMapping("/salary/")
public class SalaryController {
    // Logging related variables are initialized here
    private static Logger log = GlobalLogger.getLogger();
    private static String className = SalaryController.class.getName();

    /**
     * SalaryLogic class is autowired using spring annotation. This is
     * the business class is being used in this controller
     */
    @Autowired
    SalaryLogic salaryLogic;

    @RequestMapping(value = "calculate", method = RequestMethod.GET)
    public String calculateForm(Model model) {
        // Set the html page titple
        model.addAttribute("pageTitle", "Poornama Transport Service - Salary");
        log.debug("[" + className + "] calculateForm()");
        // Return the calculate form
        return "salary/calculate";
    }

    @RequestMapping(value = "calculate", method = RequestMethod.POST)
    public String createJob(Model model, HttpServletRequest request) {
        // Call the business logic class to calculate the salary
        salaryLogic.calculateSalary(request);
        // Set the salary table for the front-end
        model.addAttribute("salaryTable", salaryLogic.getSalaryTable(request));
        // Set the html page titple
        model.addAttribute("pageTitle", "Poornama Transport Service - Salary");
        // Return the report view
        return "salary/result";
    }
}

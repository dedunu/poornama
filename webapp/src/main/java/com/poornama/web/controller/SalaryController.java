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
    private static Logger log = GlobalLogger.getLogger();
    private static String className = SalaryController.class.getName();

    @Autowired
    SalaryLogic salaryLogic;

    @RequestMapping(value = "calculate", method = RequestMethod.GET)
    public String calculateForm(Model model) {
        model.addAttribute("pageTitle", "Poornama Transport Service - Salary");
        log.debug("[" + className + "] calculateForm()");
        return "salary/calculate";
    }

    @RequestMapping(value = "calculate", method = RequestMethod.POST)
    public String createJob(Model model, HttpServletRequest request) {
        salaryLogic.calculateSalary(request);
        model.addAttribute("salaryTable", salaryLogic.getSalaryTable(request));
        model.addAttribute("pageTitle", "Poornama Transport Service - Salary");
        return "salary/result";
    }
}

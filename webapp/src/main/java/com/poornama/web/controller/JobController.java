package com.poornama.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.poornama.logic.object.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poornama.api.logging.GlobalLogger;

@Controller
@RequestMapping("/job/")
public class JobController {
	private static Logger log = GlobalLogger.getLogger();
	private static String className = JobController.class.getName();

	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String createForm(Model model) {
		JobTemplateLogic jobTemplateLogic = new JobTemplateLogic();
		model.addAttribute("jobTemplateList",
				jobTemplateLogic.getJobTemplateSelectList());

		EmployeeLogic employeeLogic = new EmployeeLogic();
		model.addAttribute("driverList",
				employeeLogic.getDriverSelectList());
		model.addAttribute("cleanerList",
				employeeLogic.getCleanerSelectList());

		model.addAttribute("pageTitle", "Poornama Transport Service - Job");

		log.debug("[" + className + "] createForm()");
		return "job/create";
	}

	@RequestMapping(value = "details/{jobTemplateId}", method = RequestMethod.POST, produces = "application/json")
	public void getJobTemplateDetailsAJAX(@PathVariable("jobTemplateId") String jobTemplateId, HttpServletResponse response) throws IOException {
		JobTemplateLogic jobTemplateLogic = new JobTemplateLogic();
		response.getWriter().print(jobTemplateLogic.getJobTemplateDetails(jobTemplateId));
		log.debug("[" + className + "] getJobTemplateDetailsAJAX()");
	}

}

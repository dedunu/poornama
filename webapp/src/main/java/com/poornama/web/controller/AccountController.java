package com.poornama.web.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.poornama.api.logging.GlobalLogger;

@Controller
@RequestMapping("/account/")
public class AccountController {
	private static Logger log = GlobalLogger.getLogger();
	private static String className = AccountController.class.getName();

	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("pageTitle", "Poornama Transport Service - Account");
		log.debug("[" + className + "] list()");
		return "account/list";
	}
}
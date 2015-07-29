package com.poornama.web.controller;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.logic.object.UserRoleLogic;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by dedunu on 7/29/15.
 */
@Controller
@RequestMapping("/expense/")
public class ExpenseController {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = ExpenseController.class.getName();

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createForm(Model model) {
        model.addAttribute("pageTitle", "Poornama Transport Service - Expense");
        log.debug("[" + className + "] createForm()");
        return "expense/create";
    }
}

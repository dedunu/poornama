package com.poornama.web.controller;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.presentation.Notification;
import com.poornama.api.presentation.NotificationType;
import com.poornama.logic.object.EmployeeLogic;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by dedunu on 10/24/14.
 */
@Controller
@RequestMapping("/employee/")
public class EmployeeController {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = EmployeeController.class.getName();

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createForm(Model model, HttpSession session) {
        EmployeeLogic employeeLogic = new EmployeeLogic();
        model.addAttribute("employeeList", employeeLogic.getEmployeeTypeSelectList());
        log.debug("[" + className + "] createForm()");
        return "employee/create";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createEmployee(Model model, HttpSession session, HttpServletRequest request) {
        EmployeeLogic employeeLogic = new EmployeeLogic();
        Notification notification = employeeLogic.createPatient(request);
        model.addAttribute("message", notification.getMessage());
        if (notification.getNotificationType() == NotificationType.DANGER) {
            log.error("[" + className + "] createEmployee: failed");
            return "notify/danger";
        }
        if (notification.getNotificationType() == NotificationType.SUCCESS) {
            log.info("[" + className + "] createEmployee: success");
            return "notify/success";
        }
        log.fatal("[" + className + "] createEmployee: cannot reach this phrase");
        return "redirect:/";
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String editForm(Model model, @PathVariable String id, HttpSession session) {
        model.addAttribute("id", id);
        return "employee/edit";
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String deleteForm(Model model, @PathVariable String id, HttpSession session) {
        model.addAttribute("id", id);
        return "employee/delete";
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String searchForm(Model model, HttpServletResponse response) throws IOException {
        EmployeeLogic employeeLogic = new EmployeeLogic();
        String table = employeeLogic.getEmployeeTable("");
        model.addAttribute("table", table);
        log.debug("[" + className + "] searchForm()");
        return "employee/search";
    }

    @RequestMapping(value = "search/{name}", method = RequestMethod.POST)
    public void searchAJAX(Model model, @PathVariable("name") String name, HttpServletResponse response) throws IOException {
        EmployeeLogic employeeLogic = new EmployeeLogic();
        String table = employeeLogic.getEmployeeTable(name);
        response.getWriter().print(table);
        log.debug("[" + className + "] searchAJAX()");
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String editEmployee(Model model, HttpSession session) {
        EmployeeLogic employeeLogic = new EmployeeLogic();
        model.addAttribute("employeeList", employeeLogic.getEmployeeTypeSelectList());
        log.debug("[" + className + "] editEmployee()");
        return "user/edit";
    }
}
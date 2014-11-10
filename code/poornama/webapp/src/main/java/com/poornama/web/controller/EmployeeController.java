package com.poornama.web.controller;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.Employee;
import com.poornama.api.presentation.Notification;
import com.poornama.api.presentation.NotificationType;
import com.poornama.logic.object.EmployeeLogic;
import com.poornama.logic.object.EmployeeTypeLogic;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dedunu on 10/24/14.
 */
@Controller
@RequestMapping("/employee/")
public class EmployeeController {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = EmployeeController.class.getName();

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createForm(Model model) {
        EmployeeTypeLogic employeeTypeLogic = new EmployeeTypeLogic();
        model.addAttribute("employeeTypeList", employeeTypeLogic.getEmployeeTypeSelectList());
        log.debug("[" + className + "] createForm()");
        return "employee/create";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createEmployee(Model model, HttpServletRequest request) {
        EmployeeLogic employeeLogic = new EmployeeLogic();
        Notification notification = employeeLogic.createEmployee(request);
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

    @RequestMapping(value = "edit/{employeeId}", method = RequestMethod.GET)
    public String editForm(Model model, @PathVariable("employeeId") String employeeId) {
        EmployeeTypeLogic employeeTypeLogic = new EmployeeTypeLogic();
        EmployeeLogic employeeLogic = new EmployeeLogic();
        Employee employee;
        employee = employeeLogic.getEmployee(employeeId);

        if (employee == null) {
            log.error("[" + className + "] editForm: retrieving Employee failed");
        }

        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date dateOfBirth = employee.getDateOfBirth();
        Date dateOfJoining = employee.getDateOfJoining();

        model.addAttribute("employeeTypeList", employeeTypeLogic.getEmployeeTypeSelectList());
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("firstName", employee.getFirstName());
        model.addAttribute("lastName", employee.getLastName());
        model.addAttribute("employeeType", employee.getEmployeeType().getId());
        model.addAttribute("nic", employee.getNic());
        model.addAttribute("address", employee.getAddress());
        model.addAttribute("dateOfBirth", dateFormat.format(dateOfBirth));
        model.addAttribute("dateOfJoining", dateFormat.format(dateOfJoining));
        model.addAttribute("description", employee.getDescription());
        model.addAttribute("telephone", employee.getTelephoneNumber());
        model.addAttribute("emergencyContact", employee.getEmergencyContact());
        return "employee/edit";
    }

    @RequestMapping(value = "edit/{employeeId}", method = RequestMethod.POST)
    public String editEmployee(Model model, @PathVariable("employeeId") String employeeId, HttpServletRequest request) {
        EmployeeLogic employeeLogic = new EmployeeLogic();
        Notification notification = employeeLogic.editEmployee(request, employeeId);
        log.debug("[" + className + "] editEmployee()");
        model.addAttribute("message", notification.getMessage());
        if (notification.getNotificationType() == NotificationType.DANGER) {
            log.error("[" + className + "] editEmployee: failed");
            return "notify/danger";
        }
        if (notification.getNotificationType() == NotificationType.SUCCESS) {
            log.info("[" + className + "] editEmployee: success");
            return "notify/success";
        }
        log.fatal("[" + className + "] editEmployee: cannot reach this phrase");
        return "redirect:/";
    }

    @RequestMapping(value = "delete/{employeeId}", method = RequestMethod.GET)
    public String deleteForm(Model model, @PathVariable("employeeId") String employeeId) {
        EmployeeLogic employeeLogic = new EmployeeLogic();
        Employee employee;
        employee = employeeLogic.getEmployee(employeeId);
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date dateOfBirth = employee.getDateOfBirth();
        Date dateOfJoining = employee.getDateOfJoining();
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("firstName", employee.getFirstName());
        model.addAttribute("lastName", employee.getLastName());
        model.addAttribute("employeeType", employee.getEmployeeType().getDisplayName());
        model.addAttribute("nic", employee.getNic());
        model.addAttribute("address", employee.getAddress());
        model.addAttribute("dateOfBirth", dateFormat.format(dateOfBirth));
        model.addAttribute("dateOfJoining", dateFormat.format(dateOfJoining));
        model.addAttribute("description", employee.getDescription());
        model.addAttribute("telephone", employee.getTelephoneNumber());
        model.addAttribute("emergencyContact", employee.getEmergencyContact());
        return "employee/delete";
    }

    @RequestMapping(value = "delete/{employeeId}", method = RequestMethod.POST)
    public String deleteEmployee(Model model, @PathVariable("employeeId") String employeeId) {
        EmployeeLogic employeeLogic = new EmployeeLogic();
        Notification notification = employeeLogic.deleteEmployee(employeeId);
        switch (notification.getNotificationType()) {
            case DANGER:
                model.addAttribute("message", notification.getMessage());
                log.error("[" + className + "] deleteEmployee: error in deleting Employee");
                return "notify/danger";
            case SUCCESS:
                model.addAttribute("message", notification.getMessage());
                log.info("[" + className + "] deleteEmployee: deleted Employee successfully");
                return "notify/success";
            default:
                model.addAttribute("message", "Something went wrong. Please contact developer.");
                log.error("[" + className + "] deleteEmployee: fatal error in deleting Employee");
                return "notify/danger";
        }
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String searchForm(Model model) throws IOException {
        EmployeeLogic employeeLogic = new EmployeeLogic();
        String table = employeeLogic.getEmployeeTable("");
        model.addAttribute("table", table);
        log.debug("[" + className + "] searchForm()");
        return "employee/search";
    }

    @RequestMapping(value = "search/{name}", method = RequestMethod.POST)
    public void searchAJAX(@PathVariable("name") String name, HttpServletResponse response) throws IOException {
        EmployeeLogic employeeLogic = new EmployeeLogic();
        String table = employeeLogic.getEmployeeTable(name);
        response.getWriter().print(table);
        log.debug("[" + className + "] searchAJAX()");
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public void searchAJAXAll(HttpServletResponse response) throws IOException {
        searchAJAX("", response);
        log.debug("[" + className + "] searchAJAXAll()");
    }

}
package com.poornama.web.controller;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.presentation.Notification;
import com.poornama.api.presentation.NotificationType;
import com.poornama.data.dao.EmployeeDAO;
import com.poornama.data.objects.Employee;
import com.poornama.logic.object.EmployeeLogic;
import com.poornama.logic.object.EmployeeTypeLogic;
import com.poornama.logic.object.UserLogic;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dedunu on 10/24/14.
 */
@Controller
@RequestMapping("/user/")
public class UserController {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = UserController.class.getName();

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createForm(Model model) {
        EmployeeTypeLogic employeeTypeLogic = new EmployeeTypeLogic();
        model.addAttribute("userRoleList", employeeTypeLogic.getEmployeeTypeSelectList());
        log.debug("[" + className + "] createForm()");
        return "user/create";
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
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Employee employee;
        try {
            employee = employeeDAO.getById(Integer.parseInt(employeeId));
        } catch (Exception e) {
            log.error("[" + className + "] editForm: error in retrieving Employee by Id");
            model.addAttribute("message", "Something went wrong with Employee data. Please try again.");
            return "notify/danger";
        }
        EmployeeTypeLogic employeeTypeLogic = new EmployeeTypeLogic();
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
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Employee employee;
        try {
            employee = employeeDAO.getById(Integer.parseInt(employeeId));
        } catch (Exception e) {
            log.error("[" + className + "] editForm: error in retrieving Employee by Id");
            model.addAttribute("message", "Something went wrong with Employee data. Please try again.");
            return "notify/danger";
        }
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

    @RequestMapping(value = "settings", method = RequestMethod.GET)
    public String settingPage() throws IOException {
        log.debug("[" + className + "] settingPage()");
        return "user/settings";
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String searchForm(Model model) throws IOException {
        UserLogic userLogic = new UserLogic();
        String table = userLogic.getUserTable();
        model.addAttribute("table", table);
        log.debug("[" + className + "] searchForm()");
        return "user/search";
    }
}

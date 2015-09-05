package com.poornama.web.controller;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.Employee;
import com.poornama.api.presentation.Notification;
import com.poornama.api.presentation.NotificationType;
import com.poornama.logic.object.EmployeeLogic;
import com.poornama.logic.object.EmployeeTypeLogic;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @author dedunu
 */
@Controller
@RequestMapping("/employee/")
public class EmployeeController {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = EmployeeController.class.getName();

    @Autowired
    EmployeeLogic employeeLogic;

    @Autowired
    EmployeeTypeLogic employeeTypeLogic;

    /**
     * Returns the create form for the employee entity
     *
     * @param model Model
     * @return view path as a String
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createForm(Model model) {
        try {
            model.addAttribute("employeeTypeList", employeeTypeLogic.getEmployeeTypeSelectList());
            model.addAttribute("pageTitle", "Poornama Transport Service - Employee");
            log.debug("[" + className + "] createForm()");
            return "employee/create";
        } catch (Exception e) {
            log.error("[" + className + "]" + e.getMessage());
            return "redirect:/system/error";
        }
    }

    /**
     * Creates the employee and shows the notification
     *
     * @param model   Model
     * @param request HttpServletRequest
     * @return view path as a String
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createEmployee(Model model, HttpServletRequest request) {
        try {
            Notification notification = employeeLogic.createEmployee(request);
            model.addAttribute("message", notification.getMessage());
            model.addAttribute("pageTitle", "Poornama Transport Service - Employee");
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
        } catch (Exception e) {
            log.error("[" + className + "]" + e.getMessage());
            return "redirect:/system/error";
        }
    }

    /**
     * Returns the edit form for employee entity
     *
     * @param model      Model
     * @param employeeId String
     * @return view path as a String
     */
    @RequestMapping(value = "edit/{employeeId}", method = RequestMethod.GET)
    public String editForm(Model model, @PathVariable("employeeId") String employeeId) {
        try {
            Employee employee;
            employee = employeeLogic.getEmployee(employeeId);

            if (employee == null) {
                log.error("[" + className + "] editForm: retrieving Employee failed");
            }

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
            model.addAttribute("pageTitle", "Poornama Transport Service - Employee");
            return "employee/edit";
        } catch (Exception e) {
            log.error("[" + className + "]" + e.getMessage());
            return "redirect:/system/error";
        }
    }

    /**
     * Edits the employee and show the notification
     *
     * @param model      Model
     * @param employeeId String
     * @param request    HttpServletRequest
     * @return view path as a String
     */
    @RequestMapping(value = "edit/{employeeId}", method = RequestMethod.POST)
    public String editEmployee(Model model, @PathVariable("employeeId") String employeeId, HttpServletRequest request) {
        try {
            Notification notification = employeeLogic.editEmployee(request, employeeId);
            log.debug("[" + className + "] editEmployee()");
            model.addAttribute("pageTitle", "Poornama Transport Service - Employee");
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
        } catch (Exception e) {
            log.error("[" + className + "]" + e.getMessage());
            return "redirect:/system/error";
        }
    }

    /**
     * Show the delete confirmation form for the employee entity
     *
     * @param model      Model
     * @param employeeId String
     * @return view path as a String
     */
    @RequestMapping(value = "delete/{employeeId}", method = RequestMethod.GET)
    public String deleteForm(Model model, @PathVariable("employeeId") String employeeId) {
        try {
            Employee employee;
            employee = employeeLogic.getEmployee(employeeId);
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
            model.addAttribute("pageTitle", "Poornama Transport Service - Employee");
            return "employee/delete";
        } catch (Exception e) {
            log.error("[" + className + "]" + e.getMessage());
            return "redirect:/system/error";
        }
    }

    /**
     * Deletes the employee and show the notification
     *
     * @param model      Model
     * @param employeeId String
     * @return view path as a String
     */
    @RequestMapping(value = "delete/{employeeId}", method = RequestMethod.POST)
    public String deleteEmployee(Model model, @PathVariable("employeeId") String employeeId) {
        try {
            Notification notification = employeeLogic.deleteEmployee(employeeId);
            model.addAttribute("pageTitle", "Poornama Transport Service - Employee");
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
        } catch (Exception e) {
            log.error("[" + className + "]" + e.getMessage());
            return "redirect:/system/error";
        }
    }

    /**
     * Shows the search form for the employee entity
     *
     * @param model Model
     * @return view path as a String
     * @throws IOException
     */
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String searchForm(Model model) throws IOException {
        try {
            String table = employeeLogic.getEmployeeTable("");
            model.addAttribute("table", table);
            model.addAttribute("pageTitle", "Poornama Transport Service - Employee");
            log.debug("[" + className + "] searchForm()");
            return "employee/search";
        } catch (Exception e) {
            log.error("[" + className + "]" + e.getMessage());
            return "redirect:/system/error";
        }
    }

    /**
     * Returns HTML table for the search page depending the search query
     *
     * @param name     String
     * @param response HttpServletResponse
     * @throws IOException
     */
    @RequestMapping(value = "search/{name}", method = RequestMethod.POST)
    public void searchAJAX(@PathVariable("name") String name, HttpServletResponse response) throws IOException {
        try {
            String table = employeeLogic.getEmployeeTable(name);
            response.getWriter().print(table);
            log.debug("[" + className + "] searchAJAX()");
        } catch (Exception e) {
            log.error("[" + className + "]" + e.getMessage());
        }
    }

    /**
     * Returns HTML table for the search page
     *
     * @param response HttpServletResponse
     * @throws IOException
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public void searchAJAXAll(HttpServletResponse response) throws IOException {
        try {
            searchAJAX("", response);
            log.debug("[" + className + "] searchAJAXAll()");
        } catch (Exception e) {
            log.error("[" + className + "]" + e.getMessage());
        }
    }

}
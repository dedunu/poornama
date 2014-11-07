package com.poornama.web.controller;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.presentation.Notification;
import com.poornama.api.presentation.NotificationType;
import com.poornama.data.dao.EmployeeDAO;
import com.poornama.data.objects.Employee;
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
import javax.servlet.http.HttpSession;
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
    public String createForm(Model model, HttpSession session) {
        EmployeeTypeLogic employeeTypeLogic = new EmployeeTypeLogic();
        model.addAttribute("employeeList", employeeTypeLogic.getEmployeeTypeSelectList());
        log.debug("[" + className + "] createForm()");
        return "employee/create";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createEmployee(Model model, HttpSession session, HttpServletRequest request) {
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
    public String editForm(Model model, @PathVariable("employeeId") String employeeId, HttpSession session) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Employee employee;
        try {
            employee = employeeDAO.getById(Integer.parseInt(employeeId));
        } catch (Exception e){
            log.error("[" + className + "] editForm: error in retrieving Employee by Id");
            model.addAttribute("message","Something went wrong with Employee data. Please try again.");
            return "notify/danger";
        }
        EmployeeTypeLogic employeeTypeLogic = new EmployeeTypeLogic();
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date dateOfBirth = employee.getDateOfBirth();
        Date dateOfJoining = employee.getDateOfJoining();

        model.addAttribute("employeeList", employeeTypeLogic.getEmployeeTypeSelectList());
        model.addAttribute("employeeId", employeeId);
        model.addAttribute("firstName", employee.getFirstName());
        model.addAttribute("lastName", employee.getLastName());
        model.addAttribute("employeeType",employee.getEmployeeType().getId());
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
    public String editEmployee(Model model, @PathVariable("employeeId") String employeeId, HttpSession session, HttpServletRequest request) {
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
    public String deleteForm(Model model, @PathVariable("employeeId") String employeeId, HttpSession session) {
        model.addAttribute("employeeId", employeeId);
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


}
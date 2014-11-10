package com.poornama.web.controller;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.User;
import com.poornama.api.presentation.Notification;
import com.poornama.api.presentation.NotificationType;
import com.poornama.logic.object.EmployeeLogic;
import com.poornama.logic.object.UserLogic;
import com.poornama.logic.object.UserRoleLogic;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

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
        UserRoleLogic userRoleLogic = new UserRoleLogic();
        model.addAttribute("userRoleList", userRoleLogic.getUserRoleSelectList());
        log.debug("[" + className + "] createForm()");
        return "user/create";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createUser(Model model, HttpServletRequest request) {
        EmployeeLogic employeeLogic = new EmployeeLogic();
        Notification notification = employeeLogic.createEmployee(request);
        model.addAttribute("message", notification.getMessage());
        if (notification.getNotificationType() == NotificationType.DANGER) {
            log.error("[" + className + "] createUser: failed");
            return "notify/danger";
        }
        if (notification.getNotificationType() == NotificationType.SUCCESS) {
            log.info("[" + className + "] createUser: success");
            return "notify/success";
        }
        log.fatal("[" + className + "] createUser: cannot reach this phrase");
        return "redirect:/";
    }

    @RequestMapping(value = "edit/{userId}", method = RequestMethod.GET)
    public String editForm(Model model, @PathVariable("userId") String userId) {
        UserLogic userLogic = new UserLogic();
        User user;
        try {
            user = userLogic.getUserById(userId);
        } catch (Exception e) {
            log.error("[" + className + "] editForm: error in retrieving User by Id");
            model.addAttribute("message", "Something went wrong with User data. Please try again.");
            return "notify/danger";
        }
        UserRoleLogic userRoleLogic = new UserRoleLogic();
        model.addAttribute("userRoleList", userRoleLogic.getUserRoleSelectList());
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("displayName", user.getDisplayName());
        model.addAttribute("userRole", user.getUserRole().getId());
        return "user/edit";
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

    @RequestMapping(value = "delete/{userId}", method = RequestMethod.GET)
    public String deleteForm(Model model, @PathVariable("userId") String userId) {
        UserLogic userLogic = new UserLogic();
        User user;
        try {
            user = userLogic.getUserById(userId);
        } catch (Exception e) {
            log.error("[" + className + "] deleteForm: error in retrieving User by Id");
            model.addAttribute("message", "Something went wrong with User data. Please try again.");
            return "notify/danger";
        }
        model.addAttribute("userId", userId);
        model.addAttribute("userName", user.getUserName());
        model.addAttribute("displayName", user.getDisplayName());
        model.addAttribute("userRole", user.getUserRole().getDisplayName());
        return "user/delete";
    }

    @RequestMapping(value = "delete/{userId}", method = RequestMethod.POST)
    public String deleteUser(Model model, @PathVariable("userId") String userId) {
        UserLogic userLogic = new UserLogic();
        Notification notification = userLogic.deleteUser(userId);
        switch (notification.getNotificationType()) {
            case DANGER:
                model.addAttribute("message", notification.getMessage());
                log.error("[" + className + "] deleteUser: error in deleting User");
                return "notify/danger";
            case SUCCESS:
                model.addAttribute("message", notification.getMessage());
                log.info("[" + className + "] deleteUser: deleted User successfully");
                return "notify/success";
            default:
                model.addAttribute("message", "Something went wrong. Please contact developer.");
                log.error("[" + className + "] deleteUser: fatal error in deleting User");
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

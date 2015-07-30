package com.poornama.web.controller;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.Client;
import com.poornama.api.presentation.Notification;
import com.poornama.api.presentation.NotificationType;
import com.poornama.logic.object.ClientLogic;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Created by dedunu on 7/30/15.
 */
@Controller
@RequestMapping("/client/")
public class ClientController {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = ClientController.class.getName();

    @Autowired
    ClientLogic clientLogic;

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createForm(Model model) {
        model.addAttribute("pageTitle", "Poornama Transport Service - Client");
        log.debug("[" + className + "] createForm()");
        return "client/create";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createClient(Model model, HttpServletRequest request) {
        Notification notification = clientLogic.createClient(request);
        model.addAttribute("message", notification.getMessage());
        model.addAttribute("pageTitle", "Poornama Transport Service - Client");
        if (notification.getNotificationType() == NotificationType.DANGER) {
            log.error("[" + className + "] createClient: failed");
            return "notify/danger";
        }
        if (notification.getNotificationType() == NotificationType.SUCCESS) {
            log.info("[" + className + "] createClient: success");
            return "notify/success";
        }
        log.fatal("[" + className + "] createClient: cannot reach this phrase");
        return "redirect:/";
    }

    @RequestMapping(value = "edit/{clientId}", method = RequestMethod.GET)
    public String editForm(Model model, @PathVariable("clientId") String clientId) {
        Client client;
        client = clientLogic.getClient(clientId);

        if (client == null) {
            log.error("[" + className + "] editForm: retrieving Client failed");
        }

        model.addAttribute("clientId", client.getId());
        model.addAttribute("organizationName", client.getOrganizationName());
        model.addAttribute("address", client.getAddress());
        model.addAttribute("telephone", client.getTelephoneNumber());
        model.addAttribute("pageTitle", "Poornama Transport Service - Expense");
        return "client/edit";
    }

    @RequestMapping(value = "edit/{clientId}", method = RequestMethod.POST)
    public String editEmployee(Model model, @PathVariable("clientId") String clientId, HttpServletRequest request) {
        Notification notification = clientLogic.editClient(request, clientId);
        log.debug("[" + className + "] editEmployee()");
        model.addAttribute("pageTitle", "Poornama Transport Service - Client");
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

    @RequestMapping(value = "delete/{clientId}", method = RequestMethod.GET)
    public String deleteForm(Model model, @PathVariable("clientId") String clientId) {
        Client client;
        client = clientLogic.getClient(clientId);

        model.addAttribute("clientId", client.getId());
        model.addAttribute("organizationName", client.getOrganizationName());
        model.addAttribute("address", client.getAddress());
        model.addAttribute("telephone", client.getTelephoneNumber());
        model.addAttribute("pageTitle", "Poornama Transport Service - Client");
        return "client/delete";
    }

    @RequestMapping(value = "delete/{clientId}", method = RequestMethod.POST)
    public String deleteClient(Model model, @PathVariable("clientId") String clientId) {
        Notification notification = clientLogic.deleteClient(clientId);
        model.addAttribute("pageTitle", "Poornama Transport Service - Client");
        switch (notification.getNotificationType()) {
            case DANGER:
                model.addAttribute("message", notification.getMessage());
                log.error("[" + className + "] deleteEmployee: error in deleting Client");
                return "notify/danger";
            case SUCCESS:
                model.addAttribute("message", notification.getMessage());
                log.info("[" + className + "] deleteEmployee: deleted Client successfully");
                return "notify/success";
            default:
                model.addAttribute("message", "Something went wrong. Please contact developer.");
                log.error("[" + className + "] deleteEmployee: fatal error in deleting Client");
                return "notify/danger";
        }
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String searchForm(Model model) throws IOException {
        String table = clientLogic.getClientTable();
        model.addAttribute("table", table);
        model.addAttribute("pageTitle", "Poornama Transport Service - Client");
        log.debug("[" + className + "] searchForm()");
        return "client/search";
    }
}


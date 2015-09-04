package com.poornama.web.controller;

import com.poornama.api.logging.GlobalLogger;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * @author dedunu
 */
@Controller
@RequestMapping("/")
public class MainController {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = MainController.class.getName();

    @Autowired
    private MessageSource messageSource;

    /**
     * Evaluates the session and redirects to the logging page or dashboard
     *
     * @param model   Model
     * @param session HttpSession
     * @return view path as a String
     */
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model, HttpSession session) {
        log.debug("[" + className + "] index: index()");

        String isLoggedIn = "false";

        try {
            isLoggedIn = session.getAttribute("isLoggedIn").toString();
        } catch (NullPointerException ex) {
            session.setAttribute("isLoggedIn", "false");
            session.setAttribute("userRole", null);
            log.error("[" + className + "] index: NullPointerException");
            isLoggedIn = "false";
        }

        if (isLoggedIn.equals("true")) {
            model.addAttribute("pageTitle",
                    messageSource.getMessage("web.user.dashboard.title", null, null));
            log.debug("[" + className
                    + "] index: isLoggedIn=true, redirecting to user/dashboard");
            return "user/dashboard";
        } else {
            model.addAttribute("pageTitle",
                    "Poornama Transport Service - Login");
            log.debug("[" + className
                    + "] index: isLoggedIn=false, redirecting to user/login");
            return "user/login";
        }
    }
}
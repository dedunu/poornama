package com.poornama.web.controller;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.test.Test;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class MainController {

    private static Logger log = GlobalLogger.getLogger();
    private static String className = MainController.class.getName();

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model, HttpSession session) {
        log.debug("[" + className + "] index: index()");

        String isLoggedIn = "false";

        try {
            isLoggedIn = session.getAttribute("isLoggedIn").toString();
        } catch (NullPointerException ex) {
            session.setAttribute("isLoggedIn", "false");
            log.error("[" + className + "] index: NullPointerException");
            isLoggedIn = "false";
        }

        if (isLoggedIn.equals("true")) {
            model.addAttribute("pageTitle", "Poornama Transport Service - Dashboard");
            log.debug("[" + className + "] index: isLoggedIn=true, redirecting to user/dashboard");
            return "user/dashboard";
        } else {
            model.addAttribute("pageTitle", "Poornama Transport Service - Login");
            log.debug("[" + className + "] index: isLoggedIn=false, redirecting to user/login");
            return "user/login";
        }
    }

    @RequestMapping("test")
    public String test(Model model, HttpServletRequest request) {
        Test test = new Test();
        test.runTests();

        return "redirect:/";
    }

}
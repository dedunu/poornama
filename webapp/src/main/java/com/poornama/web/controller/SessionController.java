package com.poornama.web.controller;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.User;
import com.poornama.logic.object.UserLogic;
import com.poornama.logic.session.Authentication;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * @author dedunu
 */
@Controller
@RequestMapping("/session/")
public class SessionController {

    private static Logger log = GlobalLogger.getLogger();
    private static String className = SessionController.class.getName();

    @Autowired
    private Authentication authentication;

    @Autowired
    private MessageSource messageSource;

    /**
     * Validates the logging and initialize the session
     *
     * @param session  HttpSession
     * @param userName String
     * @param password String
     * @return view path as a String
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(HttpSession session, @RequestParam("username") String userName, @RequestParam("password") String password) {
        log.debug("[" + className + "] login: login()");

        if (session.getAttribute("isLoggedIn").equals("true")) {
            log.debug("[" + className + "] login: redirecting to home controller");
            return "redirect:/";
        } else {
            boolean isAuthenticated = authentication.doAuthenticate(userName, password);
            if (isAuthenticated) {
                UserLogic userLogic = new UserLogic();
                User user = userLogic.getUserByUserName(userName);
                session.setAttribute("displayName", user.getDisplayName());
                session.setAttribute("userId", user.getId());
                session.setAttribute("userName", userName);
                session.setAttribute("userRole", user.getUserRole().getName());
                session.setAttribute("isLoggedIn", "true");
                session.setAttribute("isLoginFailed", "false");
                log.debug("[" + className + "] login: Success, redirecting to home controller");
                return "redirect:/";
            } else {
                log.warn("[" + className + "] login: failed redirecting to login page with error");
                log.debug("[" + className + "] login: failed Username - " + userName);
                session.setAttribute("displayName", null);
                session.setAttribute("userId", null);
                session.setAttribute("userName", null);
                session.setAttribute("userRole", null);
                session.setAttribute("isLoggedIn", "false");
                session.setAttribute("isLoginFailed", "true");
                return "redirect:/";
            }
        }
    }

    /**
     * Shows access denied page
     *
     * @param model   Model
     * @param session HttpSession
     * @return view path as a String
     */
    @RequestMapping(value = "denied", method = RequestMethod.GET)
    public String accessDenied(Model model, HttpSession session) {
        try {
            session.setAttribute("message", messageSource.getMessage("web.session.denied.message", null, null));
        } catch (Exception e) {
            log.error("[" + className + "] accessDenied: exception while setting sessionAttribute");
        }
        log.warn("[" + className + "] accessDenied: Success, reidrected to notify/danger");
        model.addAttribute("pageTitle", messageSource.getMessage("web.session.denied.title", null, null));
        return "notify/danger";
    }

    /**
     * Dispose the session return the to context.root
     *
     * @param session HttpSession
     * @return view path as a String
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        try {
            session.setAttribute("displayName", null);
            session.setAttribute("userId", null);
            session.setAttribute("userName", null);
            session.setAttribute("userRole", null);
            session.setAttribute("isLoggedIn", "false");
            session.setAttribute("isLoginFailed", "false");
        } catch (Exception e) {
            log.debug("[" + className + "] logout: throw an error while logging out.");
        }
        log.debug("[" + className + "] logout: Success, redirecting to user/logic");
        return "redirect:/";
    }
}

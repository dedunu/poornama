package com.poornama.web.controller;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.User;
import com.poornama.logic.object.UserLogic;
import com.poornama.logic.session.Authentication;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by dedunu on 10/23/14.
 */
@Controller
@RequestMapping("/session/")
public class SessionController {

    private static Logger log = GlobalLogger.getLogger();
    private static String className = SessionController.class.getName();

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(HttpSession session, @RequestParam("username") String userName, @RequestParam("password") String password) {
        log.debug("[" + className + "] login: login()");

        if (session.getAttribute("isLoggedIn").equals("true")) {
            log.debug("[" + className + "] login: redirecting to home controller");
            return "redirect:/";
        } else {
            Authentication authentication = new Authentication();
            boolean isAuthenticated = authentication.doAuthenticate(userName, password);
            if (isAuthenticated) {
                UserLogic userLogic = new UserLogic();
                User user = userLogic.getUserById(userName);
                session.setAttribute("displayName", user.getDisplayName());
                session.setAttribute("userId", user.getId());
                session.setAttribute("userName", userName);
                session.setAttribute("userRole", user.getUserRole().getName());
                session.setAttribute("isLoggedIn", "true");
                session.setAttribute("isLoginFailed", "false");
                log.debug("[" + className + "] login: Success, redirecting to home controller");
                return "redirect:/";
            } else {
                log.debug("[" + className + "] login: failed redirecting to login page with error");
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

    @RequestMapping(value = "denied", method = RequestMethod.GET)
    public String accessDenied(HttpSession session) {
        try {
            session.setAttribute("message", "Sorry, the page you just requested is not available to you due to security reasons.");
        } catch (Exception e) {
            log.debug("[" + className + "] accessDenied: exception while setting sessionAttribute");
        }
        log.debug("[" + className + "] accessDenied: Success, reidrected to notify/danger");
        return "notify/danger";
    }

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

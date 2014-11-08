package com.poornama.web.controller;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.data.dao.UserDAO;
import com.poornama.data.objects.User;
import com.poornama.logic.session.Authentication;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String login(Model model, HttpSession session, @RequestParam("username") String userName, @RequestParam("password") String password) {
        log.debug("[" + className + "] login: login()");

        if (session.getAttribute("isLoggedIn").equals("true")) {
            log.debug("[" + className + "] login: redirecting to home controller");
            return "redirect:/";
        } else {
            Authentication authentication = new Authentication();
            boolean isAuthenticated = authentication.doAuthenticate(userName, password);
            if (isAuthenticated) {
                UserDAO userDAO = new UserDAO();
                User user = userDAO.getByUserName(userName);
                session.setAttribute("displayName", user.getDisplayName());
                session.setAttribute("userId", user.getId());
                session.setAttribute("userName", userName);
                session.setAttribute("isLoggedIn", "true");
                session.setAttribute("isLoginFailed", "false");
                log.debug("[" + className + "] login: Success, redirecting to home controller");
                return "redirect:/";
            } else {
                log.debug("[" + className + "] login: failed redirecting to login page with error");
                session.setAttribute("displayName", null);
                session.setAttribute("userId", null);
                session.setAttribute("userName", null);
                session.setAttribute("isLoggedIn", "false");
                session.setAttribute("isLoginFailed", "true");
                return "redirect:/";
            }
        }
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(Model model, HttpSession session) {
        try {
            session.setAttribute("displayName", null);
            session.setAttribute("userId", null);
            session.setAttribute("userName", null);
            session.setAttribute("isLoggedIn", "false");
            session.setAttribute("isLoginFailed", "false");
        } catch (Exception e) {
            log.debug("[" + className + "] logout: throw an error while logging out.");
        }
        log.debug("[" + className + "] logout: Success, redirecting to user/logic");
        return "redirect:/";
    }
}

package com.poornama.api.security;

import com.poornama.api.logging.GlobalLogger;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dedunu on 11/8/14.
 */
public class RoleValidator {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = RoleValidator.class.getName();

    public void validate(HttpSession session, HttpServletRequest request, HttpServletResponse response, String role) throws IOException {
        List<String> roles = new ArrayList<>();
        roles.add(role);
        validate(session, request, response, roles);
    }

    public void validate(HttpSession session, HttpServletRequest request, HttpServletResponse response, List<String> roles)
            throws IOException {
        String sessionRole = (String) session.getAttribute("userRole");
        if (!roles.contains(sessionRole)) {
            log.warn("[" + className + "] validate: role validation failed");

            response.sendRedirect(request.getContextPath() + "/session/denied");
        }
        log.debug("[" + className + "] validate: " + sessionRole);
    }
}

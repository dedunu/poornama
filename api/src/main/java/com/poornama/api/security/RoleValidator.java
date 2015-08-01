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

    /**
     * Validates a session for given role using HttpSession, if validation fails redirects to error page.
     *
     * @param session
     * @param request
     * @param response
     * @param role
     * @throws IOException
     */
    public void validate(HttpSession session, HttpServletRequest request, HttpServletResponse response, String role) throws IOException {
        List<String> roleList = new ArrayList<String>();
        roleList.add(role);
        // Calls the same method with different signature in RoleValidator class
        validate(session, request, response, roleList);
    }

    /**
     * Validates a session for given role list using HttpSession, if validation fails redirects to error page.
     *
     * @param session
     * @param request
     * @param response
     * @param roleList
     * @throws IOException
     */
    public void validate(HttpSession session, HttpServletRequest request, HttpServletResponse response, List<String> roleList)
            throws IOException {
        // Retrieves role from Session
        String sessionRole = (String) session.getAttribute("userRole");
        // Evaluvates whether role is uncluded in roleList
        if (!roleList.contains(sessionRole)) {
            log.warn("[" + className + "] validate: role validation failed");
            // Redirects to the access denied page if validation fails
            response.sendRedirect(request.getContextPath() + "/session/denied");
        }
        // Does nothing if validations is passed
        log.debug("[" + className + "] validate: " + sessionRole);
    }
}

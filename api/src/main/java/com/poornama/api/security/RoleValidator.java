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
     * @param session  HttpSession
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param role     String
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
     * @param session  HttpSession
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param roleList List<String>
     * @throws IOException
     */
    public void validate(HttpSession session, HttpServletRequest request, HttpServletResponse response, List<String> roleList)
            throws IOException {
        try {
            // Retrieves role from Session
            String sessionRole = (String) session.getAttribute("userRole");
            // Evaluates whether role is included in roleList
            if (!roleList.contains(sessionRole)) {
                log.warn("[" + className + "] validate: role validation failed");
                // Redirects to the access denied page if validation fails
                response.sendRedirect(request.getContextPath() + "/session/denied");
            }
            // Does nothing if validations is passed
            log.debug("[" + className + "] validate: " + sessionRole);
        } catch (Exception e) {
            log.error("[" + className + "] validate: Error ");
            response.sendRedirect(request.getContextPath() + "/system/error");
        }
    }

    /**
     * Returns true if current user's role contains in roleList
     *
     * @param session  HttpSession
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @param roleList List<String>
     * @return true if current user's role contains in roleList
     * @throws IOException
     */
    public boolean isVisible(HttpSession session, HttpServletRequest request, HttpServletResponse response, List<String> roleList)
            throws IOException {
        boolean result = false;

        try {
            // Retrieves role from Session
            String sessionRole = (String) session.getAttribute("userRole");
            // Evaluates whether role is included in roleList
            if (roleList.contains(sessionRole)) {
                log.debug("[" + className + "] isVisible: role validation passed");
                // Redirects to the access denied page if validation fails
                result = true;
            }
        } catch (Exception e) {
            log.error("[" + className + "] isVisible: Error " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/system/error");
        }

        return result;
    }


}

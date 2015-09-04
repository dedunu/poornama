package com.poornama.web.controller;

import com.poornama.api.logging.GlobalLogger;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author dedunu
 */
@Controller
@RequestMapping("/system/")
public class SystemController {

    private static Logger log = GlobalLogger.getLogger();
    private static String className = SystemController.class.getName();

    @Autowired
    private MessageSource messageSource;

    /**
     * Returns internal log page
     *
     * @param model Model
     * @return view path as a String
     */
    @RequestMapping(value = "logs", method = RequestMethod.GET)
    public String getLogs(Model model) {
        model.addAttribute("pageTitle", "Poornama Transport Service - Log");
        return "system/logs";
    }

    /**
     * Returns log page content to the logging page
     *
     * @param response HttpServletResponse
     * @throws IOException
     */
    @RequestMapping(value = "logsAJAX", method = RequestMethod.GET)
    public void searchAJAX(HttpServletResponse response) throws IOException {
        File file = new File(System.getProperty("user.home") + "/poornama.log");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            response.getWriter().println(line);
        }
        bufferedReader.close();
        fileReader.close();
    }

    /**
     * Shows error page with home button
     *
     * @param model Model
     * @return view path as a String
     */
    @RequestMapping(value = "error", method = RequestMethod.GET)
    public String error(Model model) {
        log.error("[" + className + "] accessDenied: Success, redirected to notify/danger");
        model.addAttribute("message", "Oops. Something went wrong. Please contact administrator.");
        model.addAttribute("pageTitle", "Poornama Transport Service - Error");
        return "notify/danger";
    }
}

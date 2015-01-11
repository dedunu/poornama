package com.poornama.web.controller;

import com.poornama.api.logging.GlobalLogger;
import org.apache.log4j.Logger;
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
 * Created by dedunu on 11/11/14.
 */
@Controller
@RequestMapping("/system/")
public class SystemController {

    private static Logger log = GlobalLogger.getLogger();
    private static String className = SystemController.class.getName();

    @RequestMapping(value = "logs", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("pageTitle", "Poornama Transport Service - Log");
        return "system/logs";
    }

    @RequestMapping(value = "logsAJAX", method = RequestMethod.GET)
    public void searchAJAX(HttpServletResponse response) throws IOException {
        File file = new File("/Users/dedunu/Documents/code/poornama/poornama.log");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while((line = bufferedReader.readLine()) != null){
            response.getWriter().println(line);
        }
        bufferedReader.close();
        fileReader.close();
    }
}

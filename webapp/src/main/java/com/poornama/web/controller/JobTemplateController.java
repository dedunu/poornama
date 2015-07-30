package com.poornama.web.controller;

import com.poornama.api.logging.GlobalLogger;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by dedunu on 7/29/15.
 */
@Controller
@RequestMapping("/jobTemplate/")
public class JobTemplateController {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = JobTemplateController.class.getName();
}

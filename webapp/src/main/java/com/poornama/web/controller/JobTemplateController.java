package com.poornama.web.controller;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.JobTemplate;
import com.poornama.api.presentation.Notification;
import com.poornama.api.presentation.NotificationType;
import com.poornama.logic.object.ClientLogic;
import com.poornama.logic.object.JobTemplateLogic;
import com.poornama.logic.object.JobTypeLogic;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author dedunu
 */
@Controller
@RequestMapping("/jobTemplate/")
public class JobTemplateController {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = JobTemplateController.class.getName();

    @Autowired
    ClientLogic clientLogic;

    @Autowired
    JobTypeLogic jobTypeLogic;

    @Autowired
    JobTemplateLogic jobTemplateLogic;

    /**
     * Returns the create form for the job template entity
     *
     * @param model Model
     * @return view path as a String
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createForm(Model model) {
        try {
            model.addAttribute("jobTypeList", jobTypeLogic.getJobTypeSelectList());
            model.addAttribute("clientList", clientLogic.getClientSelectList());
            model.addAttribute("pageTitle", "Poornama Transport Service - Job Template");
            log.debug("[" + className + "] createForm()");
            return "job/template/create";
        } catch (Exception e) {
            log.error("[" + className + "]" + e.getMessage());
            return "redirect:/system/error";
        }
    }

    /**
     * Creates the job template and shows the notification
     *
     * @param model   Model
     * @param request HttpServletRequest
     * @return view path as a String
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createJobTemplate(Model model, HttpServletRequest request) {
        try {
            Notification notification = jobTemplateLogic.createJobTemplate(request);
            model.addAttribute("message", notification.getMessage());
            model.addAttribute("pageTitle", "Poornama Transport Service - Job Template");
            if (notification.getNotificationType() == NotificationType.DANGER) {
                log.error("[" + className + "] createJobTemplate: failed");
                return "notify/danger";
            }
            if (notification.getNotificationType() == NotificationType.SUCCESS) {
                log.info("[" + className + "] createJobTemplate: success");
                return "notify/success";
            }
            log.fatal("[" + className + "] createJobTemplate: cannot reach this phrase");
            return "redirect:/";
        } catch (Exception e) {
            log.error("[" + className + "]" + e.getMessage());
            return "redirect:/system/error";
        }
    }

    /**
     * Returns the edit form for job template entity
     *
     * @param model         Model
     * @param jobTemplateId String
     * @return view path as a String
     */
    @RequestMapping(value = "edit/{jobTemplateId}", method = RequestMethod.GET)
    public String editForm(Model model, @PathVariable("jobTemplateId") String jobTemplateId) {
        try {
            JobTemplate jobTemplate;
            jobTemplate = jobTemplateLogic.getJobTemplate(jobTemplateId);

            if (jobTemplate == null) {
                log.error("[" + className + "] editForm: retrieving Job Template failed");
            }

            model.addAttribute("jobTypeList", jobTypeLogic.getJobTypeSelectList());
            model.addAttribute("clientList", clientLogic.getClientSelectList());
            model.addAttribute("jobTemplateId", jobTemplate.getId());
            model.addAttribute("client", jobTemplate.getClient().getId());
            model.addAttribute("jobType", jobTemplate.getJobType().getId());
            model.addAttribute("displayName", jobTemplate.getDisplayName());
            model.addAttribute("fromLocation", jobTemplate.getFromLocation());
            model.addAttribute("toLocation", jobTemplate.getToLocation());
            model.addAttribute("containerSize", jobTemplate.getContainerSize());
            model.addAttribute("labourCharges", jobTemplate.getLabourCharges().toBigInteger().toString());
            model.addAttribute("hireCharges", jobTemplate.getHireCharges().toBigInteger().toString());
            model.addAttribute("hourlyDetentionCharges", jobTemplate.getHourlyDetentionCharges().toBigInteger().toString());
            model.addAttribute("dailyContainerCharges", jobTemplate.getDailyContainerCharges().toBigInteger().toString());
            model.addAttribute("freeHours", jobTemplate.getFreeHours());
            model.addAttribute("distance", jobTemplate.getDistance());
            model.addAttribute("pageTitle", "Poornama Transport Service - Job Template");
            return "job/template/edit";
        } catch (Exception e) {
            log.error("[" + className + "]" + e.getMessage());
            return "redirect:/system/error";
        }
    }

    /**
     * Edits the job template and show the notification
     *
     * @param model         Model
     * @param jobTemplateId String
     * @param request       HttpServletRequest
     * @return view path as a String
     */
    @RequestMapping(value = "edit/{jobTemplateId}", method = RequestMethod.POST)
    public String editJobTemplate(Model model, @PathVariable("jobTemplateId") String jobTemplateId, HttpServletRequest request) {
        try {
            Notification notification = jobTemplateLogic.editJobTemplate(request, jobTemplateId);
            log.debug("[" + className + "] editJobTemplate()");
            model.addAttribute("pageTitle", "Poornama Transport Service - Job Template");
            model.addAttribute("message", notification.getMessage());
            if (notification.getNotificationType() == NotificationType.DANGER) {
                log.error("[" + className + "] editJobTemplate: failed");
                return "notify/danger";
            }
            if (notification.getNotificationType() == NotificationType.SUCCESS) {
                log.info("[" + className + "] editJobTemplate: success");
                return "notify/success";
            }
            log.fatal("[" + className + "] editJobTemplate: cannot reach this phrase");
            return "redirect:/";
        } catch (Exception e) {
            log.error("[" + className + "]" + e.getMessage());
            return "redirect:/system/error";
        }
    }

    /**
     * Show the delete confirmation form for the job template entity
     *
     * @param model         Model
     * @param jobTemplateId String
     * @return view path as a String
     */
    @RequestMapping(value = "delete/{jobTemplateId}", method = RequestMethod.GET)
    public String deleteForm(Model model, @PathVariable("jobTemplateId") String jobTemplateId) {
        try {
            JobTemplate jobTemplate;
            jobTemplate = jobTemplateLogic.getJobTemplate(jobTemplateId);

            model.addAttribute("jobTemplateId", jobTemplate.getId());
            model.addAttribute("client", jobTemplate.getClient().getOrganizationName());
            model.addAttribute("jobType", jobTemplate.getJobType().getDisplayName());
            model.addAttribute("displayName", jobTemplate.getDisplayName());
            model.addAttribute("fromLocation", jobTemplate.getFromLocation());
            model.addAttribute("toLocation", jobTemplate.getToLocation());
            model.addAttribute("containerSize", jobTemplate.getContainerSize() + "\"");
            model.addAttribute("labourCharges", jobTemplate.getLabourCharges().toBigInteger().toString());
            model.addAttribute("hireCharges", jobTemplate.getHireCharges().toBigInteger().toString());
            model.addAttribute("hourlyDetentionCharges", jobTemplate.getHourlyDetentionCharges().toBigInteger().toString());
            model.addAttribute("dailyContainerCharges", jobTemplate.getDailyContainerCharges().toBigInteger().toString());
            model.addAttribute("freeHours", jobTemplate.getFreeHours());
            model.addAttribute("distance", jobTemplate.getDistance());
            model.addAttribute("pageTitle", "Poornama Transport Service - Job Template");
            return "job/template/delete";
        } catch (Exception e) {
            log.error("[" + className + "]" + e.getMessage());
            return "redirect:/system/error";
        }
    }

    /**
     * Deletes the job template and show the notification
     *
     * @param model         Model
     * @param jobTemplateId String
     * @return view path as a String
     */
    @RequestMapping(value = "delete/{jobTemplateId}", method = RequestMethod.POST)
    public String deleteJobTemplate(Model model, @PathVariable("jobTemplateId") String jobTemplateId) {
        try {
            Notification notification = jobTemplateLogic.deleteJobTemplate(jobTemplateId);
            model.addAttribute("pageTitle", "Poornama Transport Service - Job Template");
            switch (notification.getNotificationType()) {
                case DANGER:
                    model.addAttribute("message", notification.getMessage());
                    log.error("[" + className + "] deleteJobTemplate: error in deleting Job Template");
                    return "notify/danger";
                case SUCCESS:
                    model.addAttribute("message", notification.getMessage());
                    log.info("[" + className + "] deleteJobTemplate: deleted Job Template successfully");
                    return "notify/success";
                default:
                    model.addAttribute("message", "Something went wrong. Please contact developer.");
                    log.error("[" + className + "] deleteJobTemplate: fatal error in deleting Job Template");
                    return "notify/danger";
            }
        } catch (Exception e) {
            log.error("[" + className + "]" + e.getMessage());
            return "redirect:/system/error";
        }
    }

    /**
     * Shows the search form for the job template entity
     *
     * @param model Model
     * @return view path as a String
     * @throws IOException
     */
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String searchForm(Model model) throws IOException {
        try {
            String table = jobTemplateLogic.getJobTemplateTable();
            model.addAttribute("table", table);
            model.addAttribute("pageTitle", "Poornama Transport Service - Job Template");
            log.debug("[" + className + "] searchForm()");
            return "job/template/search";
        } catch (Exception e) {
            log.error("[" + className + "]" + e.getMessage());
            return "redirect:/system/error";
        }
    }
}

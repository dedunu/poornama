package com.poornama.web.controller;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.Vehicle;
import com.poornama.api.presentation.Notification;
import com.poornama.api.presentation.NotificationType;
import com.poornama.logic.object.VehicleLogic;
import com.poornama.logic.object.VehicleTypeLogic;
import org.apache.log4j.Logger;
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
@RequestMapping("/vehicle/")
public class VehicleController {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = VehicleController.class.getName();

    /**
     * Returns the create form for the vehicle entity
     *
     * @param model Model
     * @return view path as a String
     */
    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createForm(Model model) {
        VehicleTypeLogic vehicleTypeLogic = new VehicleTypeLogic();
        model.addAttribute("vehicleTypeList", vehicleTypeLogic.getVehicleTypeSelectList());
        model.addAttribute("pageTitle", "Poornama Transport Service - Vehicle");
        log.debug("[" + className + "] createForm()");
        return "vehicle/create";
    }

    /**
     * Creates the vehicle and shows the notification
     *
     * @param model   Model
     * @param request HttpServletRequest
     * @return view path as a String
     */
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createVehicle(Model model, HttpServletRequest request) {
        VehicleLogic vehicleLogic = new VehicleLogic();
        Notification notification = vehicleLogic.createVehicle(request);
        model.addAttribute("message", notification.getMessage());
        model.addAttribute("pageTitle", "Poornama Transport Service - Vehicle");
        if (notification.getNotificationType() == NotificationType.DANGER) {
            log.error("[" + className + "] createVehicle: failed");
            return "notify/danger";
        }
        if (notification.getNotificationType() == NotificationType.SUCCESS) {
            log.info("[" + className + "] createVehicle: success");
            return "notify/success";
        }
        log.fatal("[" + className + "] createVehicle: cannot reach this phrase");
        return "redirect:/";
    }

    /**
     * Returns the edit form for vehicle entity
     *
     * @param model     Model
     * @param vehicleId String
     * @return view path as a String
     */
    @RequestMapping(value = "edit/{vehicleId}", method = RequestMethod.GET)
    public String editForm(Model model, @PathVariable("vehicleId") String vehicleId) {
        VehicleLogic vehicleLogic = new VehicleLogic();
        VehicleTypeLogic vehicleTypeLogic = new VehicleTypeLogic();
        Vehicle vehicle;
        try {
            vehicle = vehicleLogic.getVehicleById(vehicleId);
        } catch (Exception e) {
            log.error("[" + className + "] editForm: error in retrieving Vehicle by Id");
            model.addAttribute("message", "Something went wrong with Vehicle data. Please try again.");
            return "notify/danger";
        }

        model.addAttribute("vehicleId", vehicle.getId());
        model.addAttribute("vehicleNumber", vehicle.getVehicleNumber());
        model.addAttribute("vehicleType", vehicle.getVehicleType().getId());
        model.addAttribute("vehicleTypeList", vehicleTypeLogic.getVehicleTypeSelectList());
        model.addAttribute("pageTitle", "Poornama Transport Service - Vehicle");
        return "vehicle/edit";
    }

    /**
     * Edits the vehicle and show the notification
     *
     * @param model     Model
     * @param vehicleId String
     * @param request   HttpServletRequest
     * @return view path as a String
     */
    @RequestMapping(value = "edit/{vehicleId}", method = RequestMethod.POST)
    public String editVehicle(Model model, @PathVariable("vehicleId") String vehicleId, HttpServletRequest request) {
        VehicleLogic vehicleLogic = new VehicleLogic();
        Notification notification = vehicleLogic.editVehicle(request, vehicleId);
        log.debug("[" + className + "] editVehicle()");
        model.addAttribute("pageTitle", "Poornama Transport Service - Vehicle");
        model.addAttribute("message", notification.getMessage());
        if (notification.getNotificationType() == NotificationType.DANGER) {
            log.error("[" + className + "] editVehicle: failed");
            return "notify/danger";
        }
        if (notification.getNotificationType() == NotificationType.SUCCESS) {
            log.info("[" + className + "] editVehicle: success");
            return "notify/success";
        }
        log.fatal("[" + className + "] editVehicle: cannot reach this phrase");
        return "redirect:/";
    }

    /**
     * Show the delete confirmation form for the vehicle entity
     *
     * @param model     Model
     * @param vehicleId String
     * @return view path as a String
     */
    @RequestMapping(value = "delete/{vehicleId}", method = RequestMethod.GET)
    public String deleteForm(Model model, @PathVariable("vehicleId") String vehicleId) {
        VehicleLogic vehicleLogic = new VehicleLogic();
        Vehicle vehicle;
        try {
            vehicle = vehicleLogic.getVehicleById(vehicleId);
        } catch (Exception e) {
            log.error("[" + className + "] deleteForm: error in retrieving Vehicle by Id");
            model.addAttribute("message", "Something went wrong with Vehicle data. Please try again.");
            return "notify/danger";
        }

        model.addAttribute("pageTitle", "Poornama Transport Service - Vehicle");
        model.addAttribute("vehicleId", vehicleId);
        model.addAttribute("vehicleNumber", vehicle.getVehicleNumber());
        model.addAttribute("vehicleType", vehicle.getVehicleType().getDisplayName());

        return "vehicle/delete";
    }

    /**
     * Deletes the employee and show the notification
     *
     * @param model     Model
     * @param vehicleId String
     * @return view path as a String
     */
    @RequestMapping(value = "delete/{vehicleId}", method = RequestMethod.POST)
    public String deleteVehicle(Model model, @PathVariable("vehicleId") String vehicleId) {
        VehicleLogic vehicleLogic = new VehicleLogic();
        Notification notification = vehicleLogic.deleteVehicle(vehicleId);
        model.addAttribute("pageTitle", "Poornama Transport Service - Vehicle");
        switch (notification.getNotificationType()) {
            case DANGER:
                model.addAttribute("message", notification.getMessage());
                log.error("[" + className + "] deleteVehicle: error in deleting Vehicle");
                return "notify/danger";
            case SUCCESS:
                model.addAttribute("message", notification.getMessage());
                log.info("[" + className + "] deleteVehicle: deleted Vehicle successfully");
                return "notify/success";
            default:
                model.addAttribute("message", "Something went wrong. Please contact developer.");
                log.error("[" + className + "] deleteVehicle: fatal error in deleting Vehicle");
                return "notify/danger";
        }
    }

    /**
     * Shows the search form for the employee entity
     *
     * @param model Model
     * @return view path as a String
     * @throws IOException
     */
    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String searchForm(Model model) throws IOException {
        VehicleLogic vehicleLogic = new VehicleLogic();
        String table = vehicleLogic.getVehicleTable();
        model.addAttribute("table", table);
        model.addAttribute("pageTitle", "Poornama Transport Service - Vehicle");
        log.debug("[" + className + "] searchForm()");
        return "vehicle/search";
    }
}

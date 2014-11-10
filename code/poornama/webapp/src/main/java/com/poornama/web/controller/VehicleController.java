package com.poornama.web.controller;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.presentation.Notification;
import com.poornama.api.presentation.NotificationType;
import com.poornama.data.dao.EmployeeDAO;
import com.poornama.data.dao.VehicleDAO;
import com.poornama.api.objects.Employee;
import com.poornama.api.objects.Vehicle;
import com.poornama.api.objects.VehicleType;
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
 * Created by dedunu on 10/24/14.
 */
@Controller
@RequestMapping("/vehicle/")
public class VehicleController {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = EmployeeController.class.getName();

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createForm(Model model) {
        VehicleTypeLogic vehicleTypeLogic = new VehicleTypeLogic();
        model.addAttribute("vehicleTypeList", vehicleTypeLogic.getVehicleTypeSelectList());
        log.debug("[" + className + "] createForm()");
        return "vehicle/create";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String createVehicle(Model model, HttpServletRequest request) {
        VehicleLogic vehicleLogic = new VehicleLogic();
        Notification notification = vehicleLogic.createVehicle(request);
        model.addAttribute("message", notification.getMessage());
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

    @RequestMapping(value = "edit/{vehicleId}", method = RequestMethod.GET)
    public String editForm(Model model, @PathVariable("vehicleId") String vehicleId) {
        VehicleDAO vehicleDAO = new VehicleDAO();
        VehicleTypeLogic vehicleTypeLogic = new VehicleTypeLogic();
        Vehicle vehicle;
        try {
            vehicle = vehicleDAO.getById(Integer.parseInt(vehicleId));
        } catch (Exception e) {
            log.error("[" + className + "] editForm: error in retrieving Vehicle by Id");
            model.addAttribute("message", "Something went wrong with Vehicle data. Please try again.");
            return "notify/danger";
        }
        VehicleType vehicleType = new VehicleType();

        model.addAttribute("vehicleId", vehicle.getId());
        model.addAttribute("vehicleNumber", vehicle.getVehicleNumber());
        model.addAttribute("vehicleType", vehicle.getVehicleType().getId());
        model.addAttribute("vehicleTypeList", vehicleTypeLogic.getVehicleTypeSelectList());

        return "vehicle/edit";
    }

    @RequestMapping(value = "edit/{vehicleId}", method = RequestMethod.POST)
    public String editVehicle(Model model, @PathVariable("vehicleId") String vehicleId, HttpServletRequest request) {
        VehicleLogic vehicleLogic = new VehicleLogic();
        Notification notification = vehicleLogic.editVehicle(request, vehicleId);
        log.debug("[" + className + "] editVehicle()");
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

    @RequestMapping(value = "delete/{vehicleId}", method = RequestMethod.GET)
    public String deleteForm(Model model, @PathVariable("vehicleId") String vehicleId) {
        VehicleDAO vehicleDAO = new VehicleDAO();
        Vehicle vehicle;

        EmployeeDAO employeeDAO = new EmployeeDAO();
        Employee employee;
        try {
            vehicle = vehicleDAO.getById(Integer.parseInt(vehicleId));
        } catch (Exception e) {
            log.error("[" + className + "] deleteForm: error in retrieving Vehicle by Id");
            model.addAttribute("message", "Something went wrong with Vehicle data. Please try again.");
            return "notify/danger";
        }

        model.addAttribute("vehicleId", vehicleId);
        model.addAttribute("vehicleNumber", vehicle.getVehicleNumber());
        model.addAttribute("vehicleType", vehicle.getVehicleType().getDisplayName());

        return "vehicle/delete";
    }

    @RequestMapping(value = "delete/{vehicleId}", method = RequestMethod.POST)
    public String deleteVehicle(Model model, @PathVariable("vehicleId") String vehicleId) {
        VehicleLogic vehicleLogic = new VehicleLogic();
        Notification notification = vehicleLogic.deleteVehicle(vehicleId);
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
                log.error("[" + className + "] deleteVehicle: fatal error in deleting Employee");
                return "notify/danger";
        }
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String searchForm(Model model) throws IOException {
        VehicleLogic vehicleLogic = new VehicleLogic();
        String table = vehicleLogic.getVehicleTable();
        model.addAttribute("table", table);
        log.debug("[" + className + "] searchForm()");
        return "vehicle/search";
    }
}

package com.poornama.logic.object;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.Vehicle;
import com.poornama.api.objects.VehicleType;
import com.poornama.api.presentation.DataTableGenerator;
import com.poornama.api.presentation.Notification;
import com.poornama.api.presentation.NotificationType;
import com.poornama.data.dao.VehicleDAO;
import com.poornama.data.dao.VehicleTypeDAO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author dedunu
 */
@Service
public class VehicleLogic {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = VehicleLogic.class.getName();

    /**
     * Creates the vehicle using HttpServletRequest and returns Notification object with message
     *
     * @param request HttpServletRequest
     * @return the notification with the message
     */
    public Notification createVehicle(HttpServletRequest request) {
        VehicleDAO vehicleDAO = new VehicleDAO();
        VehicleTypeDAO vehicleTypeDAO = new VehicleTypeDAO();
        Vehicle vehicle = new Vehicle();
        VehicleType vehicleType;
        Notification notification = new Notification();

        int vehicleTypeId = 0;

        try {
            vehicleTypeId = Integer.parseInt(request.getParameter("vehicleType"));
        } catch (Exception e) {
            log.error("[" + className + "] createVehicle: Error in parsing createVehicleTypeId");
        }

        vehicleType = vehicleTypeDAO.getById(vehicleTypeId);

        vehicle.setVehicleNumber(request.getParameter("vehicleNumber"));
        vehicle.setVehicleType(vehicleType);

        try {
            vehicleDAO.create(vehicle);
            notification.setNotificationType(NotificationType.SUCCESS);
            notification.setMessage("Vehicle created successfully.");
            log.info("[" + className + "] createVehicle: created Vehicle");
        } catch (Exception e) {
            notification.setNotificationType(NotificationType.DANGER);
            notification.setMessage("Something went wrong with creating vehicle. Please try again.");
            log.error("[" + className + "] createVehicle: failed creating Vehicle");
        }

        return notification;
    }

    /**
     * Edit the vehicle using HttpServletRequest and returns Notification object with message
     *
     * @param request HttpServletRequest
     * @param vehicleId String
     * @return the notification with the message
     */
    public Notification editVehicle(HttpServletRequest request, String vehicleId) {
        VehicleDAO vehicleDAO = new VehicleDAO();
        VehicleTypeDAO vehicleTypeDAO = new VehicleTypeDAO();
        Vehicle vehicle = new Vehicle();
        VehicleType vehicleType;
        Notification notification = new Notification();
        int vehicleTypeId = 0;
        int id = 0;

        try {
            vehicleTypeId = Integer.parseInt(request.getParameter("vehicleType"));
        } catch (Exception e) {
            log.error("[" + className + "] editEmployee: Error in parsing vehicleTypeId");
        }

        try {
            id = Integer.parseInt(vehicleId);
        } catch (Exception e) {
            log.error("[" + className + "] editEmployee: Error in parsing vehicleId");
        }
        vehicleType = vehicleTypeDAO.getById(vehicleTypeId);
        vehicle.setId(id);
        vehicle.setVehicleNumber(request.getParameter("vehicleNumber"));
        vehicle.setVehicleType(vehicleType);

        try {
            vehicleDAO.update(vehicle);
            notification.setNotificationType(NotificationType.SUCCESS);
            notification.setMessage("Vehicle data updated successfully.");
            log.info("[" + className + "] editVehicle: edit Vehicle");
        } catch (Exception e) {
            notification.setNotificationType(NotificationType.DANGER);
            notification.setMessage("Something went wrong with creating Vehicle. Please try again.");
            log.error("[" + className + "] editVehicle: failed creating Vehicle");
        }

        return notification;
    }

    /**
     * Deletes the vehicle using employeeId and returns the Notification object with message
     *
     * @param vehicleId String
     * @return the notification with the message
     */
    public Notification deleteVehicle(String vehicleId) {
        Notification notification = new Notification();
        VehicleDAO vehicleDAO = new VehicleDAO();
        int id = 0;

        try {
            id = Integer.parseInt(vehicleId);
        } catch (Exception e) {
            log.error("[" + className + "] deleteVehicle: Error in parsing vehicleId");
        }

        try {
            Vehicle vehicle = vehicleDAO.getById(id);
            vehicleDAO.delete(vehicle);
            notification.setNotificationType(NotificationType.SUCCESS);
            notification.setMessage("Deleted vehicle successfully");
        } catch (Exception e) {
            notification.setNotificationType(NotificationType.DANGER);
            notification.setMessage("Deleted vehicle failed. Please try again.");
        }

        return notification;
    }

    /**
     * Returns vehicle table for controller classes as a HTML table
     *
     * @return vehicle  table as a HTML string
     */
    public String getVehicleTable() {
        List<Vehicle> vehicleList;
        VehicleDAO vehicleDAO = new VehicleDAO();
        DataTableGenerator dataTableGenerator = new DataTableGenerator();
        String table;
        vehicleList = vehicleDAO.getAll();

        table = dataTableGenerator.getStartTable();
        String dataArray[] = new String[2];
        dataArray[0] = "Vehicle Number";
        dataArray[1] = "Vehicle Type";
        table = table + dataTableGenerator.getTableHeader(dataArray);
        table = table + dataTableGenerator.getStartTableBody();

        for (Vehicle vehicle : vehicleList) {
            dataArray[0] = vehicle.getVehicleNumber();
            dataArray[1] = vehicle.getVehicleType().getDisplayName();
            table = table + dataTableGenerator.getTableBodyRow(dataArray, "edit/" + vehicle.getId(), "delete/" + vehicle.getId());
        }

        table = table + dataTableGenerator.getEndTableBody();
        table = table + dataTableGenerator.getEndTable();
        return table;
    }

    /**
     * Returns prime mover list for controller classes as select list
     *
     * @return prime mover list for controller classes as String
     */
    public String getPrimeMoverVehicleSelectList() {
        VehicleDAO vehicleDAO = new VehicleDAO();
        VehicleTypeDAO vehicleTypeDAO = new VehicleTypeDAO();
        VehicleType vehicleType = vehicleTypeDAO.getByName("prime_mover");
        List<Vehicle> vehicleList = vehicleDAO.getByVehicleType(vehicleType);
        String list = "";
        for (Vehicle vehicle : vehicleList) {
            list = list + "\t\t<option value =\"" + vehicle.getId() + "\">" + vehicle.getVehicleNumber() + "</option>\n";
        }
        log.debug("[" + className + "] getPrimeMoverVehicleSelectList()");
        return list;
    }

    /**
     * Returns the vehicle object for the given vehicleId
     *
     * @param vehicleId
     * @return Vehicle object
     */
    public Vehicle getVehicleById(String vehicleId) {
        VehicleDAO vehicleDAO = new VehicleDAO();
        Vehicle vehicle;
        int id = 0;
        try {
            id = Integer.parseInt(vehicleId);
        } catch (Exception e) {
            log.error("[" + className + "] getVehicleById() : error in parsing vechileId to integer");
        }
        vehicle = vehicleDAO.getById(id);
        return vehicle;

    }

}

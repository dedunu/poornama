package com.poornama.logic.object;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.presentation.DataTableGenerator;
import com.poornama.api.presentation.Notification;
import com.poornama.api.presentation.NotificationType;
import com.poornama.data.dao.VehicleDAO;
import com.poornama.data.dao.VehicleTypeDAO;
import com.poornama.data.objects.Vehicle;
import com.poornama.data.objects.VehicleType;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by dedunu on 11/8/14.
 */
public class VehicleLogic {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = VehicleLogic.class.getName();

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

    public String getVehicleTable(){
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

}

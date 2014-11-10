package com.poornama.logic.object;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.data.dao.VehicleTypeDAO;
import com.poornama.api.objects.VehicleType;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by dedunu on 11/8/14.
 */
public class VehicleTypeLogic {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = VehicleTypeLogic.class.getName();

    public String getVehicleTypeSelectList() {
        VehicleTypeDAO vehicleTypeDAO = new VehicleTypeDAO();
        List<VehicleType> vehicleTypeList = vehicleTypeDAO.getAll();
        String list = "";
        for (VehicleType vehicleType : vehicleTypeList) {
            list = list + "\t\t<option value =\"" + vehicleType.getId() + "\">" + vehicleType.getDisplayName() + "</option>\n";
        }
        log.debug("[" + className + "] getVehicleTypeSelectList()");
        return list;
    }
}

package com.poornama.logic.object;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.VehicleType;
import com.poornama.data.dao.VehicleTypeDAO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dedunu on 11/8/14.
 */
@Service
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

package com.poornama.logic.object;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.EmployeeType;
import com.poornama.data.dao.EmployeeTypeDAO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dedunu
 */
@Service
public class EmployeeTypeLogic {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = EmployeeTypeLogic.class.getName();

    /**
     * Returns employee type list for controller classes as select list
     *
     * @return employee type list for controller classes as String
     */
    public String getEmployeeTypeSelectList() {
        EmployeeTypeDAO employeeTypeDAO = new EmployeeTypeDAO();
        List<EmployeeType> employeeTypeList = employeeTypeDAO.getAll();
        String list = "";
        for (EmployeeType employeeType : employeeTypeList) {
            list = list + "\t\t<option value =\"" + employeeType.getId() + "\">" + employeeType.getDisplayName() + "</option>\n";
        }
        log.debug("[" + className + "] getEmployeeTypeSelectList()");
        return list;
    }
}

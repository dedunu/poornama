package com.poornama.logic;

import com.poornama.data.dao.EmployeeTypeDAO;
import com.poornama.data.objects.EmployeeType;

import java.util.List;

/**
 * Created by dedunu on 11/6/14.
 */
public class PresentationList {
    public String getEmployeeTypeSelectList() {
        EmployeeTypeDAO employeeTypeDAO = new EmployeeTypeDAO();
        List<EmployeeType> employeeTypeList = employeeTypeDAO.getAll();
        String list = "";
        for (EmployeeType employeeType : employeeTypeList) {
            list = list + "\t\t<option value =\"" + employeeType.getId() + "\">" + employeeType.getDisplayName() + "</option>\n";
        }
        return list;
    }
}

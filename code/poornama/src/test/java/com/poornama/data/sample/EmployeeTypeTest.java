package com.poornama.data.sample;

import com.poornama.data.dao.EmployeeTypeDAO;
import com.poornama.data.objects.EmployeeType;
import org.junit.Test;

/**
 * Created by dedunu on 11/6/14.
 */
public class EmployeeTypeTest {
    /*
     Owner
     Manager
     Driver
     Cleaner
     Technician
    */

    @Test
    public void runTest() {
        EmployeeTypeDAO employeeTypeDAO = new EmployeeTypeDAO();

        EmployeeType employeeType1 = new EmployeeType();
        employeeType1.setDisplayName("Owner");
        employeeType1.setName("owner");
        employeeTypeDAO.create(employeeType1);

        EmployeeType employeeType2 = new EmployeeType();
        employeeType2.setDisplayName("Manager");
        employeeType2.setName("manager");
        employeeTypeDAO.create(employeeType2);

        EmployeeType employeeType3 = new EmployeeType();
        employeeType3.setDisplayName("Driver");
        employeeType3.setName("driver");
        employeeTypeDAO.create(employeeType3);

        EmployeeType employeeType4 = new EmployeeType();
        employeeType4.setDisplayName("Cleaner");
        employeeType4.setName("cleaner");
        employeeTypeDAO.create(employeeType4);

        EmployeeType employeeType5 = new EmployeeType();
        employeeType5.setDisplayName("Technician");
        employeeType5.setName("technician");
        employeeTypeDAO.create(employeeType5);
    }
}

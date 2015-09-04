package com.poornama.data;

import com.poornama.api.objects.Configuration;
import com.poornama.api.objects.EmployeeType;
import com.poornama.api.objects.JobType;
import com.poornama.api.objects.User;
import com.poornama.api.objects.UserRole;
import com.poornama.api.objects.VehicleType;
import com.poornama.data.dao.ConfigurationDAO;
import com.poornama.data.dao.EmployeeTypeDAO;
import com.poornama.data.dao.JobTypeDAO;
import com.poornama.data.dao.UserDAO;
import com.poornama.data.dao.UserRoleDAO;
import com.poornama.data.dao.VehicleTypeDAO;

/**
 * @author dedunu
 */
public class ApplicationInitializer {

    /**
     * Initialize meta date and prepare the date base for the applicaton
     */
    public void initialize() {
        ConfigurationDAO configurationDAO = new ConfigurationDAO();

        Configuration configuration1 = new Configuration();
        configuration1.setName("driverCommission");
        configuration1.setValue("0.08");
        configurationDAO.create(configuration1);

        Configuration configuration2 = new Configuration();
        configuration2.setName("cleanerCommission");
        configuration2.setValue("0.05");
        configurationDAO.create(configuration2);

        Configuration configuration3 = new Configuration();
        configuration3.setName("driverBasicSalary");
        configuration3.setValue("30000");
        configurationDAO.create(configuration3);

        Configuration configuration4 = new Configuration();
        configuration4.setName("cleanerBasicSalary");
        configuration4.setValue("20000");
        configurationDAO.create(configuration4);

        Configuration configuration5 = new Configuration();
        configuration5.setName("employeeEPF");
        configuration5.setValue("0.08");
        configurationDAO.create(configuration5);

        Configuration configuration6 = new Configuration();
        configuration6.setName("companyEPF");
        configuration6.setValue("0.12");
        configurationDAO.create(configuration6);

        Configuration configuration7 = new Configuration();
        configuration7.setName("companyETF");
        configuration7.setValue("0.03");
        configurationDAO.create(configuration7);

        Configuration configuration8 = new Configuration();
        configuration8.setName("managerBasicSalary");
        configuration8.setValue("50000");
        configurationDAO.create(configuration8);

        Configuration configuration9 = new Configuration();
        configuration9.setName("technicianBasicSalary");
        configuration9.setValue("35000");
        configurationDAO.create(configuration9);

        EmployeeTypeDAO employeeTypeDAO = new EmployeeTypeDAO();

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

        JobTypeDAO jobTypeDAO = new JobTypeDAO();

        JobType jobType1 = new JobType();
        jobType1.setDisplayName("Import");
        jobType1.setName("import");
        jobTypeDAO.create(jobType1);

        JobType jobType2 = new JobType();
        jobType2.setDisplayName("Export");
        jobType2.setName("export");
        jobTypeDAO.create(jobType2);

        JobType jobType3 = new JobType();
        jobType3.setDisplayName("Less than Container Load - LCL");
        jobType3.setName("lcl");
        jobTypeDAO.create(jobType3);

        UserRoleDAO userRoleDAO = new UserRoleDAO();

        UserRole userRole1 = new UserRole();
        userRole1.setName("admin");
        userRole1.setDisplayName("Administrator");
        userRoleDAO.create(userRole1);

        UserRole userRole2 = new UserRole();
        userRole2.setName("manager");
        userRole2.setDisplayName("Manager");
        userRoleDAO.create(userRole2);

        UserRole userRole3 = new UserRole();
        userRole3.setName("owner");
        userRole3.setDisplayName("Owner");
        userRoleDAO.create(userRole3);

        UserRole userRole4 = new UserRole();
        userRole4.setName("clerk");
        userRole4.setDisplayName("Clerk");
        userRoleDAO.create(userRole4);

        VehicleTypeDAO vehicleTypeDAO = new VehicleTypeDAO();
        VehicleType vehicleType1 = new VehicleType();
        vehicleType1.setDisplayName("Prime Mover");
        vehicleType1.setName("prime_mover");
        vehicleTypeDAO.create(vehicleType1);

        vehicleType1 = new VehicleType();
        vehicleType1.setDisplayName("Trailer");
        vehicleType1.setName("trailer");
        vehicleTypeDAO.create(vehicleType1);

        vehicleType1 = new VehicleType();
        vehicleType1.setDisplayName("Cab");
        vehicleType1.setName("cab");
        vehicleTypeDAO.create(vehicleType1);

        vehicleType1 = new VehicleType();
        vehicleType1.setDisplayName("Van");
        vehicleType1.setName("van");
        vehicleTypeDAO.create(vehicleType1);

        UserDAO userDAO = new UserDAO();
        UserRole userRole;
        userRole = userRoleDAO.getByName("admin");

        User user1 = new User();
        user1.setDisplayName("Admin");
        user1.setUserName("admin");
        user1.setPassword("admin");
        user1.setUserRole(userRole);
        userDAO.create(user1);
    }
}

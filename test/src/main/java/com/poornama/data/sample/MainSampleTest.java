package com.poornama.data.sample;

import org.junit.Ignore;

@Ignore
public class MainSampleTest {
    public static void main(String args[]){
        UserRoleTest userRoleTest = new UserRoleTest();
        userRoleTest.runTest();
        
    	OrganizationTypeTest organizationTypeTest = new OrganizationTypeTest();
    	organizationTypeTest.runTest();
    	
        VehicleTypeTest vehicleTypeTest = new VehicleTypeTest();
        vehicleTypeTest.runTest();
        
        EmployeeTypeTest employeeTypeTest = new EmployeeTypeTest();
        employeeTypeTest.runTest();
         
        UserTest userTest = new UserTest();
        userTest.runTest();

        VehicleTest vehicleTest = new VehicleTest();
        vehicleTest.runTest();
        
        EmployeeTest employeeTest = new EmployeeTest();
        employeeTest.runTest();
        
        OrganizationTest organizationTest = new OrganizationTest();
        organizationTest.runTest();
    }
}

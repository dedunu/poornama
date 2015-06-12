package com.poornama.data.sample;

import com.poornama.api.objects.JobTemplate;
import org.junit.Ignore;

@Ignore
public class MainSampleTest {
    public static void main(String args[]){
        UserRoleTest userRoleTest = new UserRoleTest();
        userRoleTest.runTest();

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

        ClientTest clientTest = new ClientTest();
        clientTest.runTest();

        JobTypeTest jobTypeTest = new JobTypeTest();
        jobTypeTest.runTest();

        JobTemplateTest jobTemplateTest = new JobTemplateTest();
        jobTemplateTest.runTest();

        System.exit(0);
    }
}

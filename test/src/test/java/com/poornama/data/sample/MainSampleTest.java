package com.poornama.data.sample;

import org.junit.Ignore;

/**
 * Created by dedunu on 11/9/14.
 */
@Ignore
public class MainSampleTest {
    public static void main(String args[]){
        UserRoleTest userRoleTest = new UserRoleTest();
        userRoleTest.runTest();

        UserTest userTest = new UserTest();
        userTest.runTest();

        VehicleTypeTest vehicleTypeTest = new VehicleTypeTest();
        vehicleTypeTest.runTest();

        EmployeeTypeTest employeeTypeTest = new EmployeeTypeTest();
        employeeTypeTest.runTest();
    }
}

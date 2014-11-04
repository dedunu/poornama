package com.poornama.test;

/**
 * Created by dedunu on 10/23/14.
 */
public class Test {
    public static void main(String args[]){
        Test test = new Test();
        test.runTests();
    }

    public void runTests(){
        UserRoleTest userRoleTest = new UserRoleTest();
        userRoleTest.runTest();

        UserTest userTest = new UserTest();
        userTest.runTest();
    }
}

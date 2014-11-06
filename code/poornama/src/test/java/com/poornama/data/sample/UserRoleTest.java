package com.poornama.data.sample;

import com.poornama.data.dao.UserRoleDAO;
import com.poornama.data.objects.UserRole;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by dedunu on 10/23/14.
 */
@Ignore
public class UserRoleTest {

    @Test
    public void runTest() {
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
        userRole3.setName("accountant");
        userRole3.setDisplayName("Accountant");
        userRoleDAO.create(userRole3);

        UserRole userRole4 = new UserRole();
        userRole4.setName("clerk");
        userRole4.setDisplayName("Clerk");
        userRoleDAO.create(userRole4);
    }
}

package com.poornama.data.sample;

import com.poornama.data.dao.UserDAO;
import com.poornama.data.dao.UserRoleDAO;
import com.poornama.api.objects.User;
import com.poornama.api.objects.UserRole;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by dedunu on 10/23/14.
 */
@Ignore
public class UserTest {

    @Test
    public void runTest() {
        UserDAO userDAO = new UserDAO();
        UserRoleDAO userRoleDAO = new UserRoleDAO();
        UserRole userRole;

        userRole = userRoleDAO.getByName("admin");

        User user1 = new User();
        user1.setDisplayName("Dedunu Dhananjaya");
        user1.setUserName("dedunumax");
        user1.setPassword("rootroot");
        user1.setUserRole(userRole);
        userDAO.create(user1);

        User user2 = new User();
        user2.setDisplayName("Yohani Liyanage");
        user2.setUserName("yohanimax");
        user2.setPassword("rootroot");
        user2.setUserRole(userRole);
        userDAO.create(user2);

        userRole = userRoleDAO.getByName("accountant");

        User user3 = new User();
        user3.setDisplayName("Chaminda Jayaruk");
        user3.setUserName("jayaruk");
        user3.setPassword("rootroot");
        user3.setUserRole(userRole);
        userDAO.create(user3);
    }
}

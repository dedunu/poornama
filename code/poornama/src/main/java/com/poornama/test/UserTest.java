package com.poornama.test;

import com.poornama.data.dao.UserDAO;
import com.poornama.data.objects.User;

/**
 * Created by dedunu on 10/23/14.
 */
public class UserTest {
    public void runTest(){
        UserDAO userDAO = new UserDAO();

        User user1 = new User();
        user1.setDisplayName("Dedunu Dhananjaya");
        user1.setUserName("dedunumax");
        user1.setPassword("rootroot");
        userDAO.create(user1);

        User user2 = new User();
        user2.setDisplayName("Yohani Liyanage");
        user2.setUserName("yohanimax");
        user2.setPassword("rootroot");
        userDAO.create(user2);
    }
}

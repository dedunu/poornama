package com.poornam.data.dao;

import com.poornama.data.dao.UserDAO;
import com.poornama.data.objects.User;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by dedunu on 10/22/14.
 */
public class UserDAOTest {

    private UserDAO userDAO = new UserDAO();

    @Test
    public void createUser(){
        User user1 = new User();
        user1.setDisplayName("John Doe");
        user1.setUserName("doe1");
        user1.setPassword("rootroot");
        userDAO.create(user1);
        Assert.assertTrue(true);
    }

    @Test
    public void getUserByName(){
        User user1 = new User();
        user1.setDisplayName("John Doe2");
        user1.setUserName("doe2");
        user1.setPassword("rootroot");
        userDAO.create(user1);
        User user2 = userDAO.getByUserName("doe2");
        Assert.assertTrue(user2.getDisplayName().equals("John Doe2"));
    }

    @Test(expected=NullPointerException.class)
    public void getUserByNameNotExisting(){
        User user = userDAO.getByUserName("someoneElse");
        String displayName = user.getDisplayName();
    }

    @Test
    public void deleteUser(){
        User user1 = new User();
        user1.setDisplayName("John Doe");
        user1.setUserName("doe3");
        user1.setPassword("rootroot");
        userDAO.create(user1);
        User user = userDAO.getByUserName("doe3");
        userDAO.delete(user);
        Assert.assertTrue(true);
    }
}

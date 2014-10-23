package com.poornam.data.dao;

import com.poornama.data.dao.UserRoleDAO;
import com.poornama.data.objects.UserRole;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by dedunu on 10/23/14.
 */
public class UserRoleDAOTest {

    private UserRoleDAO userRoleDAO = new UserRoleDAO();

    @Test
    public void createUserRole(){
        UserRole userRole = new UserRole();
        userRole.setName("admin");
        userRole.setDisplayName("Administrator");
        userRoleDAO.create(userRole);
        Assert.assertTrue(true);
    }

    @Test
    public void deleteUser(){
        UserRole userRole = new UserRole();
        userRole.setName("manager");
        userRole.setDisplayName("Manager");
        userRoleDAO.create(userRole);
        userRoleDAO.delete(userRole);
        Assert.assertTrue(true);
    }

    @Test
    public void cleanDatabase(){
        UserRole userRole = new UserRole();
        userRole = userRoleDAO.getById(1);
        userRoleDAO.delete(userRole);
    }
}

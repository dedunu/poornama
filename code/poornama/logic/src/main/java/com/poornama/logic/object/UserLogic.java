package com.poornama.logic.object;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.User;
import com.poornama.api.objects.UserRole;
import com.poornama.api.presentation.DataTableGenerator;
import com.poornama.api.presentation.Notification;
import com.poornama.api.presentation.NotificationType;
import com.poornama.api.security.PasswordHash;
import com.poornama.data.dao.UserDAO;
import com.poornama.data.dao.UserRoleDAO;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by dedunu on 11/8/14.
 */
public class UserLogic {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = UserLogic.class.getName();

    public Notification createUser(HttpServletRequest request) {
        UserDAO userDAO = new UserDAO();
        UserRoleDAO userRoleDAO = new UserRoleDAO();
        User user = new User();
        UserRole userRole;
        Notification notification = new Notification();
        PasswordHash passwordHash = new PasswordHash();
        int userRoleId = 0;
        String hashedPassword;
        String userName;
        String password;

        try {
            userRoleId = Integer.parseInt(request.getParameter("userRole"));
        } catch (Exception e) {
            log.error("[" + className + "] createUser: Error in parsing nic");
        }

        userRole = userRoleDAO.getById(userRoleId);
        userName = request.getParameter("userName");
        password = request.getParameter("password");
        hashedPassword = passwordHash.getHash(userName, password);

        user.setDisplayName(request.getParameter("displayName"));
        user.setUserName(userName);
        user.setPassword(hashedPassword);
        user.setUserRole(userRole);

        try {
            userDAO.create(user);
            notification.setNotificationType(NotificationType.SUCCESS);
            notification.setMessage("User created successfully.");
            log.info("[" + className + "] createUser: created user");
        } catch (Exception e) {
            notification.setNotificationType(NotificationType.DANGER);
            notification.setMessage("Something went wrong with creating user. Please try again.");
            log.error("[" + className + "] createUser: failed creating user");
        }

        return notification;
    }


    public Notification deleteUser(String userId) {
        Notification notification = new Notification();
        UserDAO userDAO = new UserDAO();
        int id = 0;
        try {
            id = Integer.parseInt(userId);
        } catch (Exception e) {
            log.error("[" + className + "] deleteUser() error in parsing userId");
        }
        try {
            User user = userDAO.getById(id);
            userDAO.delete(user);
            notification.setNotificationType(NotificationType.SUCCESS);
            notification.setMessage("Deleted user successfully");
        } catch (Exception e) {
            notification.setNotificationType(NotificationType.DANGER);
            notification.setMessage("Deleted user failed. Please try again.");
        }

        return notification;
    }

    public String getUserTable() {
        List<User> userList;
        UserDAO userDAO = new UserDAO();
        DataTableGenerator dataTableGenerator = new DataTableGenerator();
        String table;
        String dataArray[] = new String[2];

        userList = userDAO.getAll();

        table = dataTableGenerator.getStartTable();
        dataArray[0] = "User";
        dataArray[1] = "Role";
        table = table + dataTableGenerator.getTableHeader(dataArray);
        table = table + dataTableGenerator.getStartTableBody();

        for (User user : userList) {
            dataArray[0] = user.getDisplayName();
            dataArray[1] = user.getUserRole().getDisplayName();
            table = table + dataTableGenerator.getTableBodyRow(dataArray, "edit/" + user.getId(), "delete/" + user.getId());
        }

        table = table + dataTableGenerator.getEndTableBody();
        table = table + dataTableGenerator.getEndTable();
        log.debug("[" + className + "] getUserTable()");
        return table;
    }

    public User getUserByUserName(String userName) {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getByUserName(userName);
        return user;
    }

    public User getUserById(String userId) {
        int id = 0;
        try {
            id = Integer.parseInt(userId);
        } catch (Exception e) {
            log.error("[" + className + "] getUserById(): error in parsing userId to string");
        }
        UserDAO userDAO = new UserDAO();
        User user = userDAO.getById(id);
        return user;
    }
}

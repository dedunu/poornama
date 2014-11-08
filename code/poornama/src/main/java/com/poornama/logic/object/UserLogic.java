package com.poornama.logic.object;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.presentation.DataTableGenerator;
import com.poornama.data.dao.UserDAO;
import com.poornama.data.objects.User;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by dedunu on 11/8/14.
 */
public class UserLogic {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = UserLogic.class.getName();

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
        return table;
    }
}

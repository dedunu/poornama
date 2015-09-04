package com.poornama.logic.object;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.UserRole;
import com.poornama.data.dao.UserRoleDAO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dedunu
 */
@Service
public class UserRoleLogic {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = UserRoleLogic.class.getName();

    /**
     * Returns user role list for controller classes as select list
     *
     * @return user role list for controller classes as String
     */
    public String getUserRoleSelectList() {
        UserRoleDAO userRoleDAO = new UserRoleDAO();
        List<UserRole> userRoleList = userRoleDAO.getAll();
        String list = "";
        for (UserRole userRole : userRoleList) {
            list = list + "\t\t<option value =\"" + userRole.getId() + "\">" + userRole.getDisplayName() + "</option>\n";
        }
        log.debug("[" + className + "] getUserRoleSelectList()");
        return list;
    }
}

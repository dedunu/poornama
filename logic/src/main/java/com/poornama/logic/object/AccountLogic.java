package com.poornama.logic.object;

import java.util.List;

import org.apache.log4j.Logger;

import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.Account;
import com.poornama.api.presentation.DashboardList;
import com.poornama.data.dao.AccountDAO;

public class AccountLogic {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = AccountLogic.class.getName();
    
    public String getAccountDashboardList(){
    	DashboardList dashboardList = new DashboardList();
    	AccountDAO accountDAO = new AccountDAO();
    	List<Account> accountList = accountDAO.getAll();
        String table;
        String dataArray[] = new String[2];
        
        table = dashboardList.getStartTable();
        dataArray[0] = "Organization";
        dataArray[1] = "Account Name";
        table = table + dashboardList.getTableHeader(dataArray);
        table = table + dashboardList.getStartTableBody();
        
    	for (Account account : accountList){
            dataArray[0] = account.getOrganization().getOrganizationName();
            dataArray[1] = account.getAccountName();
            table = table + dashboardList.getTableBodyRow(dataArray, "../edit/" + account.getId());
    	}
    	
        table = table + dashboardList.getEndTableBody();
        table = table + dashboardList.getEndTable();
        
        log.debug("[" + className + "] getUserTable()");
        return table;
    }
}

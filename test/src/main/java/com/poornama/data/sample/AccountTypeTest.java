package com.poornama.data.sample;

import org.junit.Test;

import com.poornama.api.objects.AccountType;
import com.poornama.data.dao.AccountTypeDAO;

public class AccountTypeTest {

    @Test
    public void runTest() {
    	AccountTypeDAO accountTypeDAO = new AccountTypeDAO();
    	
    	AccountType accountType1 = new AccountType();
    	accountType1.setDisplayName("Current Account");
    	accountType1.setName("ca");
    	accountTypeDAO.create(accountType1);
    	
    	AccountType accountType2 = new AccountType();
    	accountType2.setDisplayName("Savings Account");
    	accountType2.setName("sa");
    	accountTypeDAO.create(accountType2);
    	
    	AccountType accountType3 = new AccountType();
    	accountType3.setDisplayName("Expenses Account");
    	accountType3.setName("expenses");
    	accountTypeDAO.create(accountType3);
    	
    	AccountType accountType4 = new AccountType();
    	accountType4.setDisplayName("Income Account");
    	accountType4.setName("income");
    	accountTypeDAO.create(accountType4);
    }
}

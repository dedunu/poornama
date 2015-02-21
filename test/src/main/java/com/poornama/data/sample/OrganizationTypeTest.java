package com.poornama.data.sample;

import org.junit.Test;

import com.poornama.api.objects.OrganizationType;
import com.poornama.data.dao.OrganizationTypeDAO;

public class OrganizationTypeTest {
	@Test
	public void runTest(){
		OrganizationTypeDAO organizationTypeDAO = new OrganizationTypeDAO();
		
		OrganizationType organizationType1 = new OrganizationType();
		organizationType1.setDisplayName("Bank");
		organizationType1.setName("bank");
		organizationTypeDAO.create(organizationType1);
		
		OrganizationType organizationType2 = new OrganizationType();
		organizationType2.setDisplayName("Client");
		organizationType2.setName("client");
		organizationTypeDAO.create(organizationType2);
		
		OrganizationType organizationType3 = new OrganizationType();
		organizationType3.setDisplayName("Transport Services");
		organizationType3.setName("transportService");
		organizationTypeDAO.create(organizationType3);
		
		OrganizationType organizationType4 = new OrganizationType();
		organizationType4.setDisplayName("Government");
		organizationType4.setName("government");
		organizationTypeDAO.create(organizationType4);
		
		OrganizationType organizationType5 = new OrganizationType();
		organizationType5.setDisplayName("Vendors");
		organizationType5.setName("vendor");
		organizationTypeDAO.create(organizationType5);
	}
}

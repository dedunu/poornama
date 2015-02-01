package com.poornama.data.sample;

import org.junit.Test;

import com.poornama.api.objects.Organization;
import com.poornama.data.dao.OrganizationDAO;

public class OrganizationTest {

	@Test
	public void runTest(){
		OrganizationDAO organizationDAO = new OrganizationDAO();
		
		Organization organization1 = new Organization();
		organization1.setOrganizationName("Hatton National Bank");
		organization1.setTelephoneNumber("0112947874");
		organization1.setAddress("22 Delgoda, Colombo.");
		
		organizationDAO.create(organization1);
	}
}

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
		
		Organization organization2 = new Organization();
		organization2.setOrganizationName("MAS Active");
		organization2.setTelephoneNumber("0112977777");
		organization2.setAddress("287 Meegahawatta, Colombo.");
		organizationDAO.create(organization2);

		Organization organization3 = new Organization();
		organization3.setOrganizationName("Donald Transport");
		organization3.setTelephoneNumber("0112977777");
		organization3.setAddress("287 Meegahawatta, Colombo.");
		organizationDAO.create(organization3);

		Organization organization4 = new Organization();
		organization4.setOrganizationName("Income Tax Department");
		organization4.setTelephoneNumber("0112977777");
		organization4.setAddress("287 Meegahawatta, Colombo.");
		organizationDAO.create(organization4);
		
		Organization organization5 = new Organization();
		organization5.setOrganizationName("ETF/EPF");
		organization5.setTelephoneNumber("0112977777");
		organization5.setAddress("287 Meegahawatta, Colombo.");
		organizationDAO.create(organization5);

		Organization organization6 = new Organization();
		organization6.setOrganizationName("Chithra Tire Store");
		organization6.setTelephoneNumber("0112977777");
		organization6.setAddress("287 Meegahawatta, Colombo.");
		organizationDAO.create(organization6);
	}
}

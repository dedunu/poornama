package com.poornama.data.sample;

import org.junit.Test;

import com.poornama.api.objects.Organization;
import com.poornama.api.objects.OrganizationType;
import com.poornama.data.dao.OrganizationDAO;
import com.poornama.data.dao.OrganizationTypeDAO;

public class OrganizationTest {

	@Test
	public void runTest(){
		OrganizationDAO organizationDAO = new OrganizationDAO();
		OrganizationTypeDAO organizationTypeDAO = new OrganizationTypeDAO();
		OrganizationType organizationType;
		
		organizationType = organizationTypeDAO.getByName("bank");
		
		Organization organization1 = new Organization();
		organization1.setOrganizationName("Hatton National Bank");
		organization1.setTelephoneNumber("0112947874");
		organization1.setAddress("22 Delgoda, Colombo.");
		organization1.setClient(false);
		organization1.setOrganizationType(organizationType);
		organizationDAO.create(organization1);
		
		organizationType = organizationTypeDAO.getByName("client");
		
		Organization organization2 = new Organization();
		organization2.setOrganizationName("MAS Active");
		organization2.setTelephoneNumber("0112977777");
		organization2.setAddress("287 Meegahawatta, Colombo.");
		organization2.setClient(false);
		organization2.setOrganizationType(organizationType);
		organizationDAO.create(organization2);
		
		organizationType = organizationTypeDAO.getByName("transportService");
		
		Organization organization3 = new Organization();
		organization3.setOrganizationName("Donald Transport");
		organization3.setTelephoneNumber("0112977777");
		organization3.setAddress("287 Meegahawatta, Colombo.");
		organization3.setClient(false);
		organization3.setOrganizationType(organizationType);
		organizationDAO.create(organization3);
		
		organizationType = organizationTypeDAO.getByName("government");
		
		Organization organization4 = new Organization();
		organization4.setOrganizationName("Income Tax Department");
		organization4.setTelephoneNumber("0112977777");
		organization4.setAddress("287 Meegahawatta, Colombo.");
		organization4.setClient(false);
		organization4.setOrganizationType(organizationType);
		organizationDAO.create(organization4);
		
		Organization organization5 = new Organization();
		organization5.setOrganizationName("ETF/EPF");
		organization5.setTelephoneNumber("0112977777");
		organization5.setAddress("287 Meegahawatta, Colombo.");
		organization5.setClient(false);
		organization5.setOrganizationType(organizationType);
		organizationDAO.create(organization5);
		
		organizationType = organizationTypeDAO.getByName("vendor");
		
		Organization organization6 = new Organization();
		organization6.setOrganizationName("Chithra Tire Store");
		organization6.setTelephoneNumber("0112977777");
		organization6.setAddress("287 Meegahawatta, Colombo.");
		organization6.setClient(false);
		organization6.setOrganizationType(organizationType);
		organizationDAO.create(organization6);
	}
}

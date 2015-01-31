package com.poornama.data.sample;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;

import com.poornama.api.objects.Employee;
import com.poornama.api.objects.EmployeeType;
import com.poornama.data.dao.EmployeeDAO;
import com.poornama.data.dao.EmployeeTypeDAO;

public class EmployeeTest {

	@Test
	public void runTest(){
		EmployeeDAO employeeDAO = new EmployeeDAO();
		EmployeeTypeDAO employeeTypeDAO = new EmployeeTypeDAO();
		EmployeeType employeeType = new EmployeeType();
		
		employeeType = employeeTypeDAO.getByName("owner");
		
		Employee employee1 = new Employee();
		employee1.setAddress("Address1");
		employee1.setDateOfBirth(new Date());
		employee1.setDateOfJoining(new Date());
		employee1.setDescription("Description1");
		employee1.setEmergencyContact("Contact1");
		employee1.setEmployeeType(employeeType);
		employee1.setFirstName("FirstName1");
		employee1.setLastName("LastName1");
		employee1.setNic(900000001);
		employee1.setTelephoneNumber("Telephone1");
		employeeDAO.create(employee1);
		
		employeeType = employeeTypeDAO.getByName("manager");
		
		Employee employee2 = new Employee();
		employee2.setAddress("Address2");
		employee2.setDateOfBirth(new Date());
		employee2.setDateOfJoining(new Date());
		employee2.setDescription("Description2");
		employee2.setEmergencyContact("Contact2");
		employee2.setEmployeeType(employeeType);
		employee2.setFirstName("FirstName2");
		employee2.setLastName("LastName2");
		employee2.setNic(900000002);
		employee2.setTelephoneNumber("Telephone2");
		employeeDAO.create(employee2);

		employeeType = employeeTypeDAO.getByName("technician");
		
		Employee employee3 = new Employee();
		employee3.setAddress("Address3");
		employee3.setDateOfBirth(new Date());
		employee3.setDateOfJoining(new Date());
		employee3.setDescription("Description3");
		employee3.setEmergencyContact("Contact3");
		employee3.setEmployeeType(employeeType);
		employee3.setFirstName("FirstName3");
		employee3.setLastName("LastName3");
		employee3.setNic(900000003);
		employee3.setTelephoneNumber("Telephone3");
		employeeDAO.create(employee3);
	
		employeeType = employeeTypeDAO.getByName("driver");
		
		Employee employee4 = new Employee();
		employee4.setAddress("Address4");
		employee4.setDateOfBirth(new Date());
		employee4.setDateOfJoining(new Date());
		employee4.setDescription("Description4");
		employee4.setEmergencyContact("Contact4");
		employee4.setEmployeeType(employeeType);
		employee4.setFirstName("FirstName4");
		employee4.setLastName("LastName4");
		employee4.setNic(900000004);
		employee4.setTelephoneNumber("Telephone4");
		employeeDAO.create(employee4);
	
		Employee employee5 = new Employee();
		employee5.setAddress("Address5");
		employee5.setDateOfBirth(new Date());
		employee5.setDateOfJoining(new Date());
		employee5.setDescription("Description5");
		employee5.setEmergencyContact("Contact5");
		employee5.setEmployeeType(employeeType);
		employee5.setFirstName("FirstName5");
		employee5.setLastName("LastName5");
		employee5.setNic(900000005);
		employee5.setTelephoneNumber("Telephone5");
		employeeDAO.create(employee5);
	
		Employee employee6 = new Employee();
		employee6.setAddress("Address6");
		employee6.setDateOfBirth(new Date());
		employee6.setDateOfJoining(new Date());
		employee6.setDescription("Description6");
		employee6.setEmergencyContact("Contact6");
		employee6.setEmployeeType(employeeType);
		employee6.setFirstName("FirstName6");
		employee6.setLastName("LastName6");
		employee6.setNic(900000006);
		employee6.setTelephoneNumber("Telephone6");
		employeeDAO.create(employee6);
	
		employeeType = employeeTypeDAO.getByName("cleaner");
		
		Employee employee7 = new Employee();
		employee7.setAddress("Address7");
		employee7.setDateOfBirth(new Date());
		employee7.setDateOfJoining(new Date());
		employee7.setDescription("Description7");
		employee7.setEmergencyContact("Contact7");
		employee7.setEmployeeType(employeeType);
		employee7.setFirstName("FirstName7");
		employee7.setLastName("LastName7");
		employee7.setNic(900000007);
		employee7.setTelephoneNumber("Telephone7");
		employeeDAO.create(employee7);
	
		Employee employee8 = new Employee();
		employee8.setAddress("Address8");
		employee8.setDateOfBirth(new Date());
		employee8.setDateOfJoining(new Date());
		employee8.setDescription("Description8");
		employee8.setEmergencyContact("Contact8");
		employee8.setEmployeeType(employeeType);
		employee8.setFirstName("FirstName8");
		employee8.setLastName("LastName8");
		employee8.setNic(900000008);
		employee8.setTelephoneNumber("Telephone8");
		employeeDAO.create(employee8);
	
		Employee employee9 = new Employee();
		employee9.setAddress("Address9");
		employee9.setDateOfBirth(new Date());
		employee9.setDateOfJoining(new Date());
		employee9.setDescription("Description9");
		employee9.setEmergencyContact("Contact9");
		employee9.setEmployeeType(employeeType);
		employee9.setFirstName("FirstName9");
		employee9.setLastName("LastName9");
		employee9.setNic(900000009);
		employee9.setTelephoneNumber("Telephone9");
		employeeDAO.create(employee9);
	}
}

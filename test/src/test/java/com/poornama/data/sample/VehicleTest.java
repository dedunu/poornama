package com.poornama.data.sample;

import org.junit.Ignore;
import org.junit.Test;

import com.poornama.api.objects.Vehicle;
import com.poornama.api.objects.VehicleType;
import com.poornama.data.dao.VehicleDAO;
import com.poornama.data.dao.VehicleTypeDAO;

@Ignore
public class VehicleTest {

	@Test
	public void runTest() {
		VehicleDAO vehicleDAO = new VehicleDAO();
		VehicleTypeDAO vehicleTypeDAO = new VehicleTypeDAO();
		VehicleType vehicleType = null;

		vehicleType = vehicleTypeDAO.getByName("prime_mover");

		Vehicle vehicle1 = new Vehicle();
		vehicle1.setVehicleNumber("290-0475");
		vehicle1.setVehicleType(vehicleType);
		vehicleDAO.create(vehicle1);

		Vehicle vehicle2 = new Vehicle();
		vehicle2.setVehicleNumber("GA-1792");
		vehicle2.setVehicleType(vehicleType);
		vehicleDAO.create(vehicle2);

		vehicleType = vehicleTypeDAO.getByName("trailer");
		
		Vehicle vehicle3 = new Vehicle();
		vehicle3.setVehicleNumber("42-2004");
		vehicle3.setVehicleType(vehicleType);
		vehicleDAO.create(vehicle3);
		
		Vehicle vehicle4 = new Vehicle();
		vehicle4.setVehicleNumber("42-2005");
		vehicle4.setVehicleType(vehicleType);
		vehicleDAO.create(vehicle4);
		
		vehicleType = vehicleTypeDAO.getByName("cab");
	
		Vehicle vehicle5 = new Vehicle();
		vehicle5.setVehicleNumber("51-3091");
		vehicle5.setVehicleType(vehicleType);
		vehicleDAO.create(vehicle5);
		
		vehicleType = vehicleTypeDAO.getByName("van");
		
		Vehicle vehicle6 = new Vehicle();
		vehicle6.setVehicleNumber("253-0583");
		vehicle6.setVehicleType(vehicleType);
		vehicleDAO.create(vehicle6);
		
	}
}

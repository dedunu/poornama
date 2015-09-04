package com.poornama.data.dao;

import com.poornama.api.db.DatabaseSession;
import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.Vehicle;
import com.poornama.api.objects.VehicleType;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import java.util.List;

/**
 * @author dedunu
 */
public class VehicleDAO {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = VehicleDAO.class.getName();

    /**
     * Create vehicle
     *
     * @param vehicle Vehicle
     */
    public void create(Vehicle vehicle) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.save(vehicle);
        log.debug("[" + className + "] create()");
    }

    /**
     * Delete vehicle
     *
     * @param vehicle Vehicle
     */
    public void delete(Vehicle vehicle) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.delete(vehicle);
        log.debug("[" + className + "] delete()");
    }

    /**
     * Update vehicle
     *
     * @param vehicle Vehicle
     */
    public void update(Vehicle vehicle) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.update(vehicle);
        log.debug("[" + className + "] update()");
    }

    /**
     * Return vehicle by id
     *
     * @param id int
     * @return Vehicle
     */
    public Vehicle getById(int id) {
        DatabaseSession databaseSession = new DatabaseSession();
        Vehicle vehicle = (Vehicle) databaseSession.getById(Vehicle.class, id);
        log.debug("[" + className + "] getById()");
        return vehicle;
    }

    /**
     * Return vehicle by id
     *
     * @param id String
     * @return Vehicle
     */
    public Vehicle getById(String id) {
        int vehicleId = 0;
        try {
            vehicleId = Integer.parseInt(id);
        } catch (NumberFormatException ex) {
            return null;
        }
        return getById(vehicleId);
    }

    /**
     * Return all the vehicles
     *
     * @return List&lt;Vehicle&gt;
     */
    public List<Vehicle> getAll() {
        DatabaseSession databaseSession = new DatabaseSession();
        List<Vehicle> vehicleList = databaseSession.getAll(Vehicle.class);
        log.debug("[" + className + "] getAll()");
        return vehicleList;
    }

    /**
     * Returns vehicle by vehicle type
     *
     * @param vehicleType VehicleType
     * @return List&lt;Vehicle&gt;
     */
    public List<Vehicle> getByVehicleType(VehicleType vehicleType) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        Criteria criteria = databaseSession.createCriteria(Vehicle.class);
        SimpleExpression simpleExpression = Restrictions.eq("vehicleType", vehicleType);
        List<Vehicle> vehicleList = criteria.add(simpleExpression).list();
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] getByVehicleType()");
        return vehicleList;
    }
}

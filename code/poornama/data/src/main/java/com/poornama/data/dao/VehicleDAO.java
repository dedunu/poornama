package com.poornama.data.dao;

import com.poornama.api.db.DatabaseSession;
import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.Vehicle;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import java.util.List;

/**
 * Created by dedunu on 11/4/14.
 */
public class VehicleDAO {
    private static Logger log = GlobalLogger.getLogger();
    private static String className = VehicleDAO.class.getName();

    public VehicleDAO() {
        log.debug("[" + className + "] VehicleDAO: constructor()");
    }

    public void create(Vehicle vehicle) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        databaseSession.save(vehicle);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] create()");
    }

    public void delete(Vehicle vehicle) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        databaseSession.delete(vehicle);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] delete()");
    }

    public void update(Vehicle vehicle) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        databaseSession.update(vehicle);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] update()");
    }

    public Vehicle getById(int id) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        Vehicle vehicle = (Vehicle) databaseSession.getById(Vehicle.class, id);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] getById()");
        return vehicle;
    }

    public List<Vehicle> getAll() {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        List<Vehicle> vehicleList = databaseSession.getAll(Vehicle.class);
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] getAll()");
        return vehicleList;
    }

    public Vehicle getByVehicleNumber(String vehicleNumber) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        Criteria criteria = databaseSession.createCriteria(Vehicle.class);
        SimpleExpression simpleExpression = Restrictions.eq("vehicleNumber", vehicleNumber);
        Vehicle vehicle = (Vehicle) criteria.add(simpleExpression)
                .uniqueResult();
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] getByVehicleNumber()");
        return vehicle;
    }
}

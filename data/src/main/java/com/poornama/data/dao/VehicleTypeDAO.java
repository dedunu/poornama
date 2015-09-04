package com.poornama.data.dao;

import com.poornama.api.db.DatabaseSession;
import com.poornama.api.logging.GlobalLogger;
import com.poornama.api.objects.VehicleType;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

import java.util.List;

/**
 * @author dedunu
 */
public class VehicleTypeDAO {

    private static Logger log = GlobalLogger.getLogger();
    private static String className = VehicleTypeDAO.class.getName();

    /**
     * Create vehicle type
     * @param vehicleType VehicleType
     */
    public void create(VehicleType vehicleType) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.save(vehicleType);
        log.debug("[" + className + "] create()");
    }

    /**
     * Delete vehicle type
     * @param vehicleType VehicleType
     */
    public void delete(VehicleType vehicleType) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.delete(vehicleType);
        log.debug("[" + className + "] delete()");
    }

    /**
     * Update vehicle type
     * @param vehicleType VehicleType
     */
    public void update(VehicleType vehicleType) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.update(vehicleType);
        log.debug("[" + className + "] update()");
    }

    /**
     * Returns vehicle type by id
     * @param id int
     * @return VehicleType
     */
    public VehicleType getById(int id) {
        DatabaseSession databaseSession = new DatabaseSession();
        VehicleType vehicleType = (VehicleType) databaseSession.getById(VehicleType.class, id);
        log.debug("[" + className + "] getById()");
        return vehicleType;
    }

    /**
     * Returns vehicle type by name
     * @param name String
     * @return VehicleType
     */
    public VehicleType getByName(String name) {
        DatabaseSession databaseSession = new DatabaseSession();
        databaseSession.beginTransaction();
        Criteria criteria = databaseSession.createCriteria(VehicleType.class);
        SimpleExpression simpleExpression = Restrictions.eq("name", name);
        VehicleType vehicleType = (VehicleType) criteria.add(simpleExpression)
                .uniqueResult();
        databaseSession.commitTransaction();
        databaseSession.close();
        log.debug("[" + className + "] getByName()");
        return vehicleType;
    }

    /**
     * Returns all the vehicle types
     * @return List&lt;VehicleType&gt;
     */
    public List<VehicleType> getAll() {
        DatabaseSession databaseSession = new DatabaseSession();
        List<VehicleType> vehicleTypeList = databaseSession.getAll(VehicleType.class);
        log.debug("[" + className + "] getAll()");
        return vehicleTypeList;
    }

}

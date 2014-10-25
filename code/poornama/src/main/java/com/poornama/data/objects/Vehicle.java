package com.poornama.data.objects;

import javax.persistence.*;

/**
 * Created by dedunu on 10/21/14.
 */
@Entity
@Table
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String vehicleNumber;
    @ManyToOne
    @JoinColumn(name = "vehicleTypeId")
    private VehicleType vehicleType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}

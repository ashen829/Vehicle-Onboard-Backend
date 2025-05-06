package com.vehonboard.VehicleOnboard.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.vehonboard.VehicleOnboard.Util.VehicleType;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @ManyToOne
    private Make make;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    public Model(){}

    public Model(String name, VehicleType vehicleType, Make make) {
        this.name = name;
        this.vehicleType = vehicleType;
        this.make = make;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Make getMake() {
        return make;
    }

    public void setMake(Make make) {
        this.make = make;
    }












}

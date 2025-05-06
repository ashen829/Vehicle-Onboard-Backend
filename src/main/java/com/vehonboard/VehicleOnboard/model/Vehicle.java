package com.vehonboard.VehicleOnboard.model;

import com.vehonboard.VehicleOnboard.Util.FuelType;
import com.vehonboard.VehicleOnboard.Util.VehicleType;
import jakarta.persistence.*;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String regNo;

    @ManyToOne
    private Make make;

    @ManyToOne
    private Model model;

    private Integer yearOfManu;

    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VehicleImage> vehicleImages;

    public Vehicle(){

    }

    public Vehicle(int id, String regNo, Make make, Model model, Integer yearOfManu, FuelType fuelType, VehicleType vehicleType, List<VehicleImage> vehicleImages) {
        this.id = id;
        this.regNo = regNo;
        this.make = make;
        this.model = model;
        this.yearOfManu = yearOfManu;
        this.fuelType = fuelType;
        this.vehicleType = vehicleType;
        this.vehicleImages = vehicleImages;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public Make getMake() {
        return make;
    }

    public void setMake(Make make) {
        this.make = make;
    }

    public Integer getYearOfManu() {
        return yearOfManu;
    }

    public void setYearOfManu(Integer yearOfManu) {
        this.yearOfManu = yearOfManu;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public List<VehicleImage> getVehicleImages() {
        return vehicleImages;
    }

    public void setVehicleImages(List<VehicleImage> vehicleImages) {
        this.vehicleImages = vehicleImages;
    }


}

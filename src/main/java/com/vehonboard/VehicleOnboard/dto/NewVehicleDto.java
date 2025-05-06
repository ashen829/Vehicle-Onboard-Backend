package com.vehonboard.VehicleOnboard.dto;

import com.vehonboard.VehicleOnboard.Util.FuelType;
import com.vehonboard.VehicleOnboard.Util.VehicleType;
import com.vehonboard.VehicleOnboard.model.Make;
import com.vehonboard.VehicleOnboard.model.Model;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;

public class NewVehicleDto {

    private String regNo;
    private Make make;
    private Model model;
    private Integer yearOfManu;
    private FuelType fuelType;
    private VehicleType vehicleType;

    public NewVehicleDto(String regNo, Make make, Model model, Integer yearOfManu, FuelType fuelType, VehicleType vehicleType) {
        this.regNo = regNo;
        this.make = make;
        this.model = model;
        this.yearOfManu = yearOfManu;
        this.fuelType = fuelType;
        this.vehicleType = vehicleType;
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

    public Integer getYearOfManu() {
        return yearOfManu;
    }

    public void setYearOfManu(Integer yearOfManu) {
        this.yearOfManu = yearOfManu;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
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


}

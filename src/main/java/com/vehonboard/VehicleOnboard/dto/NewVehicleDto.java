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
    private Integer makeId;
    private Integer modelId;
    private Integer yearOfManu;
    private FuelType fuelType;
    private VehicleType vehicleType;

    public NewVehicleDto(String regNo, Integer makeId, Integer modelId, Integer yearOfManu, FuelType fuelType, VehicleType vehicleType) {
        this.regNo = regNo;
        this.makeId = makeId;
        this.modelId = modelId;
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

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }


    public Integer getMakeId() {
        return makeId;
    }

    public void setMakeId(Integer makeId) {
        this.makeId = makeId;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

}

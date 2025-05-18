package com.vehonboard.VehicleOnboard.dto;

import com.vehonboard.VehicleOnboard.Util.VehicleType;

public class ViewModelDto {

    private int id;
    private String name;
    private VehicleType vehicleType;

    public ViewModelDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public ViewModelDto(int id, String name, VehicleType vehicleType) {
        this.id = id;
        this.name = name;
        this.vehicleType = vehicleType;
    }

}

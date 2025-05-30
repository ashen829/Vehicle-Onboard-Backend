package com.vehonboard.VehicleOnboard.dto;

import com.vehonboard.VehicleOnboard.Util.FuelType;
import com.vehonboard.VehicleOnboard.Util.VehicleType;
import com.vehonboard.VehicleOnboard.model.Make;
import com.vehonboard.VehicleOnboard.model.Model;
import com.vehonboard.VehicleOnboard.model.VehicleImage;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;

import java.nio.file.Paths;
import java.util.List;


public class ViewVehicleDto {

    @Value("${vehicle.image.upload-dir}")
    private String uploadDir;

    private int id;
    private String regNo;
    private ViewMakeDto make;
    private Model model;
    private Integer yearOfManu;
    private FuelType fuelType;
    private VehicleType vehicleType;
    private List<ImageDto> vehicleImages;

    public ViewVehicleDto() {}
    public ViewVehicleDto(int id, String regNo, ViewMakeDto make, Model model, Integer yearOfManu, FuelType fuelType, VehicleType vehicleType, List<ImageDto> vehicleImages) {
        this.id = id;
        this.regNo = regNo;
        this.make = make;
        this.model = model;
        this.yearOfManu = yearOfManu;
        this.fuelType = fuelType;
        this.vehicleType = vehicleType;
        this.vehicleImages = vehicleImages;
    }

    public ViewMakeDto convertToMakeDto(Make make){
        ViewMakeDto dto = new ViewMakeDto();
          dto.setLogoPath(make.getLogoPath());
          dto.setName(make.getName());
          return dto;
    }

    public Integer getYearOfManu() {
        return yearOfManu;
    }

    public void setYearOfManu(Integer yearOfManu) {
        this.yearOfManu = yearOfManu;
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

    public ViewMakeDto getMake() {
        return make;
    }

    public void setMake(ViewMakeDto make) {

        this.make = make;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
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

    public List<ImageDto> getVehicleImages() {
        return vehicleImages;
    }

    public void setVehicleImages(List<ImageDto> vehicleImages) {
        this.vehicleImages = vehicleImages;
    }


}

package com.vehonboard.VehicleOnboard.service;

import com.vehonboard.VehicleOnboard.model.ApiResponse;
import com.vehonboard.VehicleOnboard.model.Vehicle;
import com.vehonboard.VehicleOnboard.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleService (VehicleRepository vehicleRepository){
        this.vehicleRepository = vehicleRepository;
    }

    @Transactional
    public ApiResponse<Vehicle> saveVehicle(Vehicle v){
        Optional<Vehicle> vehicle = vehicleRepository.findByRegNo(v.getRegNo());
        if(vehicle.isPresent()){
            return new ApiResponse<>(false, "Vehicle with same Registration number already Exists!", null);
        }
        Vehicle savedVehicle = vehicleRepository.save(v);
        return new ApiResponse<>(true, "Vehicle saved successfully", savedVehicle);
    }


}

package com.vehonboard.VehicleOnboard.controller;

import com.vehonboard.VehicleOnboard.model.ApiResponse;
import com.vehonboard.VehicleOnboard.model.Vehicle;
import com.vehonboard.VehicleOnboard.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController (VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<Vehicle>> saveVehicle(@RequestBody Vehicle vehicle) {
        ApiResponse<Vehicle> response = vehicleService.saveVehicle(vehicle);

        if (response.isStatus()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

}

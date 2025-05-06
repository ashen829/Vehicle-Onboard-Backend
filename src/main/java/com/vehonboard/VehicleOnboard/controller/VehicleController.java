package com.vehonboard.VehicleOnboard.controller;

import com.vehonboard.VehicleOnboard.Util.ImageTag;
import com.vehonboard.VehicleOnboard.dto.MakeDto;
import com.vehonboard.VehicleOnboard.dto.NewVehicleDto;
import com.vehonboard.VehicleOnboard.model.ApiResponse;
import com.vehonboard.VehicleOnboard.model.Make;
import com.vehonboard.VehicleOnboard.model.Vehicle;
import com.vehonboard.VehicleOnboard.model.VehicleImage;
import com.vehonboard.VehicleOnboard.repository.VehicleRepository;
import com.vehonboard.VehicleOnboard.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    public VehicleController (VehicleService vehicleService){
        this.vehicleService = vehicleService;
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<?>> uploadVehicleImages(
            @ModelAttribute NewVehicleDto vehicleDto,
            @RequestParam("images") List<MultipartFile> images,
            @RequestParam("tags") List<ImageTag> tags) {

        if (images.size() != tags.size()) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, "Image and tag count mismatch", null));
        }

        ApiResponse<Vehicle> response = vehicleService.saveVehicle(vehicleDto, images, tags);

        if (response.isStatus()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @PostMapping(value = "/makes", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> saveMake(@ModelAttribute MakeDto makeDto) {
        ApiResponse<Make> response = vehicleService.saveMake(makeDto);
        return ResponseEntity.ok(response);
    }


}

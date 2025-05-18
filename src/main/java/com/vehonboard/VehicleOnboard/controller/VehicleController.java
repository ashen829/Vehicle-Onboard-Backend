package com.vehonboard.VehicleOnboard.controller;

import com.vehonboard.VehicleOnboard.Util.ImageTag;
import com.vehonboard.VehicleOnboard.dto.*;
import com.vehonboard.VehicleOnboard.model.*;
import com.vehonboard.VehicleOnboard.repository.VehicleRepository;
import com.vehonboard.VehicleOnboard.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
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

        System.out.println(vehicleDto.getMakeId());
        System.out.println(vehicleDto.getModelId());

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

    @PostMapping("/models")
    public ResponseEntity<?> saveModel(@RequestBody ModelDto modelDto) {
        ApiResponse<Model> response = vehicleService.saveModel(modelDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/vehicles")
    public ResponseEntity<ApiResponse<List<ViewVehicleDto>>> getAllVehicles() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    @GetMapping("/vehicles/{id}")
    public ResponseEntity<ApiResponse<ViewVehicleDto>> getVehicleById(@PathVariable int id) {
        return ResponseEntity.ok(vehicleService.getVehicleById(id));
    }

    // Get all makes
    @GetMapping("/makes")
    public ResponseEntity<ApiResponse<List<ViewMakeDto>>> getAllMakes() {
        ApiResponse<List<ViewMakeDto>> response = vehicleService.getAllMakes();
        return new ResponseEntity<>(response, response.isStatus() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    // Get all models
    @GetMapping("/models")
    public ResponseEntity<ApiResponse<List<ViewModelDto>>> getAllModels() {
        ApiResponse<List<ViewModelDto>> response = vehicleService.getAllModels();
        return new ResponseEntity<>(response, response.isStatus() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }




}

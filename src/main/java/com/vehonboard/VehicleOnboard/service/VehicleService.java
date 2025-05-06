package com.vehonboard.VehicleOnboard.service;

import com.vehonboard.VehicleOnboard.Util.ImageTag;
import com.vehonboard.VehicleOnboard.dto.MakeDto;
import com.vehonboard.VehicleOnboard.dto.NewVehicleDto;
import com.vehonboard.VehicleOnboard.model.ApiResponse;
import com.vehonboard.VehicleOnboard.model.Make;
import com.vehonboard.VehicleOnboard.model.Vehicle;
import com.vehonboard.VehicleOnboard.model.VehicleImage;
import com.vehonboard.VehicleOnboard.repository.MakeRepository;
import com.vehonboard.VehicleOnboard.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class VehicleService {

    @Value("${vehicle.image.upload-dir}")
    private String uploadDir;

    private final VehicleRepository vehicleRepository;
    private final MakeRepository makeRepository;

    public VehicleService (VehicleRepository vehicleRepository, MakeRepository makeRepository){
        this.vehicleRepository = vehicleRepository;
        this.makeRepository = makeRepository;
    }

    @Transactional
    public ApiResponse<Vehicle> saveVehicle(NewVehicleDto dto, List<MultipartFile> images, List<ImageTag> tags){
        Optional<Vehicle> v = vehicleRepository.findByRegNo(dto.getRegNo());
        if(v.isPresent()){
            return new ApiResponse<>(false, "Vehicle with same Registration number already Exists!", null);
        }

        Vehicle vehicle = new Vehicle();
        vehicle.setRegNo(dto.getRegNo());
        vehicle.setMake(dto.getMake());
        vehicle.setModel(dto.getModel());
        vehicle.setFuelType(dto.getFuelType());
        vehicle.setYearOfManu(dto.getYearOfManu());

        List<VehicleImage> vehicleImages = new ArrayList<>();
        try{

            for(int i=0; i< images.size(); i++){
                MultipartFile image= images.get(i);
                ImageTag tag = tags.get(i);

                String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
                Path filePath = Paths.get(uploadDir + fileName);
                Files.createDirectories(filePath.getParent());
                Files.write(filePath, image.getBytes());

                VehicleImage vehicleImage = new VehicleImage();
                vehicleImage.setImageUrl(filePath.toString());
                vehicleImage.setTag(tag);
                vehicleImage.setVehicle(vehicle);
                vehicleImages.add(vehicleImage);
            }

        } catch (Exception e) {
            return new ApiResponse<>(false, "Error uploading images: " + e.getMessage(), null);
        }

        vehicle.setVehicleImages(vehicleImages);
        vehicleRepository.save(vehicle);
        return new ApiResponse<>(true, "Vehicle Added Successfully", vehicle);

    }

    public ApiResponse<Make> saveMake(MakeDto dto) {
        try {
            MultipartFile logo = dto.getLogo();
            String fileName = UUID.randomUUID() + "_" + logo.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, logo.getBytes());

            Make make = new Make();
            make.setName(dto.getName());
            make.setLogoPath(filePath.toString());

            makeRepository.save(make);

            return new ApiResponse<>(true, "Make saved successfully", make);
        } catch (IOException e) {
            return new ApiResponse<>(false, "Failed to save logo: " + e.getMessage(), null);
        }
    }

}

package com.vehonboard.VehicleOnboard.service;

import com.vehonboard.VehicleOnboard.Util.ImageTag;
import com.vehonboard.VehicleOnboard.dto.*;
import com.vehonboard.VehicleOnboard.model.*;
import com.vehonboard.VehicleOnboard.repository.MakeRepository;
import com.vehonboard.VehicleOnboard.repository.ModelRepository;
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

    @Value("${public.base-url}")
    private String baseUrl;

    private final VehicleRepository vehicleRepository;
    private final MakeRepository makeRepository;
    private final ModelRepository modelRepository;

    public VehicleService (VehicleRepository vehicleRepository, MakeRepository makeRepository, ModelRepository modelRepository){
        this.vehicleRepository = vehicleRepository;
        this.makeRepository = makeRepository;
        this.modelRepository= modelRepository;
    }

    @Transactional
    public ApiResponse<Vehicle> saveVehicle(NewVehicleDto dto, List<MultipartFile> images, List<ImageTag> tags){
        Optional<Vehicle> v = vehicleRepository.findByRegNo(dto.getRegNo());
        if(v.isPresent()){
            return new ApiResponse<>(false, "Vehicle with same Registration number already Exists!", null);
        }

        Optional<Model> modelOpt = modelRepository.findById(dto.getModelId());
        if (modelOpt.isEmpty()) {
            return new ApiResponse<>(false, "Model not found", null);
        }

        Optional<Make> makeOpt = makeRepository.findById(dto.getMakeId());
        if (makeOpt.isEmpty()) {
            return new ApiResponse<>(false, "Make not found", null);
        }

        Vehicle vehicle = new Vehicle();
        vehicle.setRegNo(dto.getRegNo());
        vehicle.setMake(makeOpt.get());
        vehicle.setModel(modelOpt.get());
        vehicle.setFuelType(dto.getFuelType());
        vehicle.setYearOfManu(dto.getYearOfManu());
        vehicle.setVehicleType(modelOpt.get().getVehicleType());

        List<VehicleImage> vehicleImages = new ArrayList<>();
        try{

            for(int i=0; i< images.size(); i++){
                MultipartFile image= images.get(i);
                ImageTag tag = tags.get(i);

                String fileName = UUID.randomUUID() + "_" + image.getOriginalFilename();
                Path filePath = Paths.get(uploadDir , fileName);
                Files.createDirectories(filePath.getParent());
                Files.write(filePath, image.getBytes());

                String publicUrl = baseUrl + "/" + fileName;

                VehicleImage vehicleImage = new VehicleImage();
                vehicleImage.setImageUrl(publicUrl);
                vehicleImage.setTag(tag);
                vehicleImage.setVehicle(vehicle);
                vehicleImages.add(vehicleImage);
            }

        } catch (Exception e) {
            return new ApiResponse<>(false, "Error uploading images: " + e.getMessage(), null);
        }

        vehicle.setVehicleImages(vehicleImages);
        vehicleRepository.save(vehicle);
        return new ApiResponse<>(true, "Vehicle Added Successfully", null);

    }

    public ApiResponse<Make> saveMake(MakeDto dto) {
        try {
            MultipartFile logo = dto.getLogo();
            String fileName = UUID.randomUUID() + "_" + logo.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, logo.getBytes());

            String publicUrl = baseUrl + "/" + fileName;

            Make make = new Make();
            make.setName(dto.getName());
            make.setLogoPath(publicUrl);

            makeRepository.save(make);

            return new ApiResponse<>(true, "Make saved successfully!", make);
        } catch (IOException e) {
            return new ApiResponse<>(false, "Failed to save logo: " + e.getMessage(), null);
        }
    }

    public ApiResponse<Model> saveModel(ModelDto dto) {
        Optional<Make> makeOpt = makeRepository.findById(dto.getMakeId());
        if (makeOpt.isEmpty()) {
            return new ApiResponse<>(false, "Make not found", null);
        }

        Model model = new Model();
        model.setName(dto.getName());
        model.setMake(makeOpt.get());
        model.setVehicleType(dto.getVehicleType());

        modelRepository.save(model);
        return new ApiResponse<>(true, "Model saved successfully", model);
    }


    public ApiResponse<List<ViewVehicleDto>> getAllVehicles() {
        try {
            List<Vehicle> vehicles = vehicleRepository.findAll();
            List<ViewVehicleDto> dtoList = new ArrayList<>();
            for (Vehicle v : vehicles) {
                dtoList.add(convertToViewVehicleDto(v));
            }
            return new ApiResponse<>(true, "Vehicles retrieved successfully", dtoList);
        } catch (Exception e) {
            return new ApiResponse<>(false, "Error fetching vehicles: " + e.getMessage(), null);
        }
    }

    public ApiResponse<ViewVehicleDto> getVehicleById(int id) {
        Optional<Vehicle> vehicleOpt = vehicleRepository.findById(id);
        if (vehicleOpt.isPresent()) {
            ViewVehicleDto dto = convertToViewVehicleDto(vehicleOpt.get());
            return new ApiResponse<>(true, "Vehicle found", dto);
        } else {
            return new ApiResponse<>(false, "Vehicle not found with ID: " + id, null);
        }
    }


    private ViewVehicleDto convertToViewVehicleDto(Vehicle vehicle) {
        ViewVehicleDto dto = new ViewVehicleDto();
        dto.setId(vehicle.getId());
        dto.setRegNo(vehicle.getRegNo());
        dto.setMake(dto.convertToMakeDto(vehicle.getMake()));
        dto.setModel(vehicle.getModel());
        dto.setYearOfManu(vehicle.getYearOfManu());
        dto.setFuelType(vehicle.getFuelType());
        dto.setVehicleType(vehicle.getVehicleType());
        List<ImageDto> imageDtos = new ArrayList<>();
        for (VehicleImage image : vehicle.getVehicleImages()) {
            ImageDto imageDto = new ImageDto(image);
            imageDtos.add(imageDto);
        }
        dto.setVehicleImages(imageDtos);
        return dto;
    }

    public ApiResponse<List<ViewMakeDto>> getAllMakes() {
        try {
            List<Make> makes = makeRepository.findAll();
            List<ViewMakeDto> dtoList = new ArrayList<>();

            for (Make make : makes) {
                ViewMakeDto dto = new ViewMakeDto();
                dto.setId(make.getId());
                dto.setName(make.getName());
                dto.setLogoPath(make.getLogoPath());

                dtoList.add(dto);
            }

            return new ApiResponse<>(true, "Makes retrieved successfully", dtoList);
        } catch (Exception e) {
            return new ApiResponse<>(false, "Error fetching makes: " + e.getMessage(), null);
        }
    }


    public ApiResponse<List<ViewModelDto>> getAllModels() {
        try {
            List<Model> models = modelRepository.findAll();
            List<ViewModelDto> dtoList = new ArrayList<>();

            for (Model model : models) {
                ViewModelDto dto = new ViewModelDto();
                dto.setId(model.getMake().getId());
                dto.setName(model.getName());
                dto.setVehicleType(model.getVehicleType());
                dtoList.add(dto);
            }

            return new ApiResponse<>(true, "Models retrieved successfully", dtoList);
        } catch (Exception e) {
            return new ApiResponse<>(false, "Error fetching models: " + e.getMessage(), null);
        }
    }

    @Transactional
    public ApiResponse<String> deleteVehicleById(int id) {
        Optional<Vehicle> vehicleOpt = vehicleRepository.findById(id);
        if (vehicleOpt.isEmpty()) {
            return new ApiResponse<>(false, "Vehicle not found with ID: " + id, null);
        }

        Vehicle vehicle = vehicleOpt.get();

        for (VehicleImage image : vehicle.getVehicleImages()) {
            try {
                Files.deleteIfExists(Paths.get(image.getImageUrl()));
            } catch (IOException e) {
                return new ApiResponse<>(false, "Failed to delete image: " + e.getMessage(), null);
            }
        }

        vehicleRepository.delete(vehicle);
        return new ApiResponse<>(true, "Vehicle deleted successfully", null);
    }


    @Transactional
    public ApiResponse<Vehicle> updateVehicle(int id, NewVehicleDto dto) {
        Optional<Vehicle> vehicleOpt = vehicleRepository.findById(id);
        if (vehicleOpt.isEmpty()) {
            return new ApiResponse<>(false, "Vehicle not found with ID: " + id, null);
        }

        Optional<Model> modelOpt = modelRepository.findById(dto.getModelId());
        Optional<Make> makeOpt = makeRepository.findById(dto.getMakeId());

        if (modelOpt.isEmpty()) {
            return new ApiResponse<>(false, "Model not found", null);
        }
        if (makeOpt.isEmpty()) {
            return new ApiResponse<>(false, "Make not found", null);
        }

        Vehicle vehicle = vehicleOpt.get();
        vehicle.setRegNo(dto.getRegNo());
        vehicle.setModel(modelOpt.get());
        vehicle.setMake(makeOpt.get());
        vehicle.setYearOfManu(dto.getYearOfManu());
        vehicle.setFuelType(dto.getFuelType());
        vehicle.setVehicleType(modelOpt.get().getVehicleType());

        vehicleRepository.save(vehicle);
        return new ApiResponse<>(true, "Vehicle updated successfully", null);
    }







}

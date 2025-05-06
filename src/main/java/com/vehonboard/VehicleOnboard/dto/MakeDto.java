package com.vehonboard.VehicleOnboard.dto;

import org.springframework.web.multipart.MultipartFile;

public class MakeDto {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getLogo() {
        return logo;
    }

    public void setLogo(MultipartFile logo) {
        this.logo = logo;
    }

    private String name;
    private MultipartFile logo;
}

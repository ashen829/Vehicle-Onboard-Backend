package com.vehonboard.VehicleOnboard.dto;

public class ViewMakeDto {
    private int id;
    private String name;
    private String logoPath;

    public ViewMakeDto() {
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

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public ViewMakeDto(int id, String name, String logoPath) {
        this.id = id;
        this.name = name;
        this.logoPath = logoPath;
    }


}

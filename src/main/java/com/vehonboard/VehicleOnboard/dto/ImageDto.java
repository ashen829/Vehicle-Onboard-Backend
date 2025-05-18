package com.vehonboard.VehicleOnboard.dto;

import com.vehonboard.VehicleOnboard.Util.ImageTag;
import com.vehonboard.VehicleOnboard.model.VehicleImage;

import java.nio.file.Paths;

public class ImageDto {
    private long id;
    private ImageTag tag;
    private String imageUrl;

    public ImageDto(VehicleImage image) {
        this.tag = image.getTag();
        String fileName = Paths.get(image.getImageUrl()).getFileName().toString();
        this.imageUrl = "http://172.20.10.3:8080/data/" + fileName;
    }

    public ImageDto() {

    }

    public ImageDto(long id, ImageTag tag, String imageUrl) {
        this.id = id;
        this.tag = tag;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ImageTag getTag() {
        return tag;
    }

    public void setTag(ImageTag tag) {
        this.tag = tag;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}

package com.vehonboard.VehicleOnboard.dto;

import com.vehonboard.VehicleOnboard.Util.ImageTag;
import com.vehonboard.VehicleOnboard.model.VehicleImage;

import java.nio.file.Paths;

public class ImageDto {
    private long id;
    private ImageTag tag;
    private String imageUrl;

    public ImageDto(VehicleImage image) {
        this.id = image.getId();
        this.tag = image.getTag();
        this.imageUrl = image.getImageUrl();
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

package com.vehonboard.VehicleOnboard.model;

import com.vehonboard.VehicleOnboard.Util.ImageTag;
import jakarta.persistence.*;

@Entity
public class VehicleImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ImageTag tag;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    public VehicleImage(){}


    public VehicleImage(Long id, ImageTag tag, String imageUrl, Vehicle vehicle) {
        this.id = id;
        this.tag = tag;
        this.imageUrl = imageUrl;
        this.vehicle = vehicle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ImageTag getTag() {
        return tag;
    }

    public void setTag(ImageTag tag) {
        this.tag = tag;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

}

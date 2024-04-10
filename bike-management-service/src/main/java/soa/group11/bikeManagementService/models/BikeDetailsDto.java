package soa.group11.bikeManagementService.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BikeDetailsDto {
    private String id;

    private double wheelSize;

    private int numberOfGears;

    private String name;

    private String brand;

    private String color;

    private int userId;

    private String startRentingDate;

    private String endRentingDate;

    private String imageData;

    private String type;

    private String suitability;

    public BikeDetailsDto() {
    }

    public BikeDetailsDto(String id, double wheelSize, int numberOfGears, String name, String brand, String color,
            int userId, String startRentingDate, String endRentingDate, String imageData, String type,
            String suitability) {
        this.id = id;
        this.wheelSize = wheelSize;
        this.numberOfGears = numberOfGears;
        this.name = name;
        this.brand = brand;
        this.color = color;
        this.userId = userId;
        this.startRentingDate = startRentingDate;
        this.endRentingDate = endRentingDate;
        this.imageData = imageData;
        this.type = type;
        this.suitability = suitability;
    }

    public BikeDetailsDto(double wheelSize, int numberOfGears, String brand, String color, String startRentingDate,
            String endRentingDate, String type, String suitability) {
        this.wheelSize = wheelSize;
        this.numberOfGears = numberOfGears;
        this.brand = brand;
        this.color = color;
        this.startRentingDate = startRentingDate;
        this.endRentingDate = endRentingDate;
        this.type = type;
        this.suitability = suitability;
    }

}

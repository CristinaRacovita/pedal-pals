package soa.group11.bikeManagementService.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BikeDto {
    private String brand;
    private Boolean isTownBike = false;
    private int userId;
    private int numberOfGears;

    public BikeDto() {}

    public BikeDto(String brand, int numberOfGears, int userId){
        this.brand = brand;
        this.numberOfGears = numberOfGears;
        this.userId = userId;
    }

    public BikeDto(String brand, Boolean isTownBike){
        this.brand = brand;
        this.isTownBike = isTownBike;
    }

    public BikeDto(String brand, int numberOfGears, Boolean isTownBike){
        this.brand = brand;
        this.isTownBike = isTownBike;
        this.numberOfGears = numberOfGears;
    }
}

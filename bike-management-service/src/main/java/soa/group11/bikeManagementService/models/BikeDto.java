package soa.group11.bikeManagementService.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BikeDto {
    private String brand;
    private Boolean isTownBike;

    public BikeDto(String brand, Boolean isTownBike){
        this.brand = brand;
        this.isTownBike = isTownBike;
    }
}

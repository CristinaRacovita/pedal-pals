package soa.group11.bikeManagementService.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BikeDto {
    private String id;
    private String brand;
    private Boolean isTownBike = false;
    private int userId;
    private int numberOfGears;
    private List<FeedbackDto> feedbacks = new ArrayList<>();

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

    public BikeDto(String id, int userId, String brand, Boolean isTownBike){
        this.id = id;
        this.userId = userId;
        this.brand = brand;
        this.isTownBike = isTownBike;
    }
}

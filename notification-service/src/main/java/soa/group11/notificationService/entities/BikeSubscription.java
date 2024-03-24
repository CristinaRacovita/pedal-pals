package soa.group11.notificationService.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Setter;
import lombok.Getter;

@Document(collection = "bike_subscriptions")
@Getter
@Setter
public class BikeSubscription {
    private int userId;
    private int numberOfGears;
    private String brand;
    private Boolean isTownBike;

    public BikeSubscription() {}

    public BikeSubscription(int userId, int numberOfGears, String brand, Boolean isTownBike){
        this.brand = brand;
        this.numberOfGears = numberOfGears;
        this.userId = userId;
        this.isTownBike = isTownBike;
    }
}

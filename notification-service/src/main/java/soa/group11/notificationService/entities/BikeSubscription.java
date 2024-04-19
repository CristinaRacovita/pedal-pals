package soa.group11.notificationService.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Setter;
import lombok.Getter;

@Document(collection = "bike_subscriptions")
@Getter
@Setter
public class BikeSubscription {
    private int userId;
    private int wheelSize;
    private int numberOfGears;
    private String startDate;
    private String endDate;
    private String brand;
    private String type;
    private String usage;

    public BikeSubscription() {
    }
    
    public BikeSubscription(int userId, int wheelSize, int numberOfGears, String startDate, String endDate,
            String brand, String type, String usage) {
        this.userId = userId;
        this.wheelSize = wheelSize;
        this.numberOfGears = numberOfGears;
        this.startDate = startDate;
        this.endDate = endDate;
        this.brand = brand;
        this.type = type;
        this.usage = usage;
    }

    @Override
    public String toString() {
        return type + " " + brand + " bike with " + wheelSize + " inch wheels and " + numberOfGears + " gears for "
                + usage.toLowerCase() + " is available between " + startDate + " - " + endDate;
    }
}
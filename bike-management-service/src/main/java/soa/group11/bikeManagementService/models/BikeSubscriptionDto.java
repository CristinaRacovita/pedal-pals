package soa.group11.bikeManagementService.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BikeSubscriptionDto {
    private int userId;
    private int wheelSize;
    private int numberOfGears;
    private String startDate;
    private String endDate;
    private String brand;
    private String type;
    private String usage;

    public BikeSubscriptionDto() {
    }

    public BikeSubscriptionDto(int userId, int wheelSize, int numberOfGears, String startDate, String endDate,
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
}
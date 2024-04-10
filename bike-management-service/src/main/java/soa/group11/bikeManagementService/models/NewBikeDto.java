package soa.group11.bikeManagementService.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewBikeDto {
    private int wheelSize;

    private int numberOfGears;

    private String name;

    private String brand;

    private String color;

    private int userId;

    private String startRentingDate;

    private String endRentingDate;

    private String type;

    private String suitability;

    public NewBikeDto() {
    }

    public NewBikeDto(int wheelSize, int numberOfGears, String name, String brand, String color, int userId,
            String startRentingDate, String endRentingDate, String type, String suitability) {
        this.wheelSize = wheelSize;
        this.numberOfGears = numberOfGears;
        this.name = name;
        this.brand = brand;
        this.color = color;
        this.userId = userId;
        this.startRentingDate = startRentingDate;
        this.endRentingDate = endRentingDate;
        this.type = type;
        this.suitability = suitability;
    }

}

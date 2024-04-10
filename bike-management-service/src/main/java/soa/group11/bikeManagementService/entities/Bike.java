package soa.group11.bikeManagementService.entities;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "bikes")
public class Bike {
    @Id
    private String id;

    private double wheelSize;

    private int numberOfGears;

    private String name;

    private String brand;

    private String color;

    private int userId;

    private Date startRentingDate;

    private Date endRentingDate;

    private BikeImage bikeImage;

    private String type;

    private String suitability;

    public Bike() {
    }

    public Bike(int userId, String brand, String type, String suitability) {
        this.userId = userId;
        this.brand = brand;
        this.suitability = suitability;
        this.type = type;
    }

    public Bike(double wheelSize, int numberOfGears, String brand, String color, Date startRentingDate,
            Date endRentingDate, String type, String suitability) {
        this.wheelSize = wheelSize;
        this.numberOfGears = numberOfGears;
        this.brand = brand;
        this.color = color;
        this.startRentingDate = startRentingDate;
        this.endRentingDate = endRentingDate;
        this.type = type;
        this.suitability = suitability;
    }

    public Bike(double wheelSize, int numberOfGears, String name, String brand, String color, int userId,
            Date startRentingDate, Date endRentingDate, String type, String suitability) {
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

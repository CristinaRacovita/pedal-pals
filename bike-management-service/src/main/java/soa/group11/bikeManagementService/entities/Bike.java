package soa.group11.bikeManagementService.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "bikes")
public class Bike {
    @Id
    private String id;

    private double weight;

    private double wheelSize;

    private int numberOfGears;

    private String breakType;

    private String brand;

    private String color;

    private int userId;

    private BikeType bikeType;

    @DBRef
    private List<Accessory> accessories;

    public Bike() {}

    public Bike(int userId, String brand, BikeType bikeType) {
        this.userId = userId;
        this.brand = brand;
        this.bikeType = bikeType;
    }
}

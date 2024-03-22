package soa.group11.bikeManagementService.entities;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import soa.group11.bikeManagementService.enums.Color;
import soa.group11.bikeManagementService.enums.Material;

@Getter
@Setter
@Entity
@Table(name = "Bike")
public class Bike {
    @Id
    private UUID id;

    private double weight;

    private double frameSize;

    private double wheelSize;

    private int numberOfGears;

    private String breakType;

    private String suspensionType;

    private String brand;

    @Enumerated(EnumType.STRING)
    private Color color;

    @Enumerated(EnumType.STRING)
    private Material material;

    private List<Accessory> accessories;

    private int userId;

    private BikeType bikeType;

    public Bike(int userId, String brand, BikeType bikeType) {
        this.userId = userId;
        this.brand = brand;
        this.bikeType = bikeType;
    }
}

package soa.group11.bikeManagementService.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "accessories")
public class Accessory {
    @Id
    private String Id;

    private String name;

    private String brand;

    public Accessory() {}

    public Accessory(String name, String brand) {
        this.name = name;
        this.brand = brand;
    }
}
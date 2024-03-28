package soa.group11.bikeManagementService.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessoryDto {
    private String name;

    private String brand;

    private String bikeId;

    public AccessoryDto() {
    }

    public AccessoryDto(String name, String brand, String bikeId) {
        this.name = name;
        this.brand = brand;
        this.bikeId = bikeId;
    }
}

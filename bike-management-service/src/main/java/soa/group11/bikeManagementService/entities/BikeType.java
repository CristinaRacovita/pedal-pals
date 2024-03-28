package soa.group11.bikeManagementService.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BikeType {
    private String type;

    private String suitability;

    public BikeType() {
    }

    public BikeType(String suitability) {
        this.suitability = suitability;
    }
}

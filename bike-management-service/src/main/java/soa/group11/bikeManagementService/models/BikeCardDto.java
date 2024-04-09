package soa.group11.bikeManagementService.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BikeCardDto {
    private String name;

    private String startRentingDate;

    private String endRentingDate;

    private String imageData;

    public BikeCardDto() {}

    public BikeCardDto(String name, String startRentingDate, String endRentingDate, String imageData) {
        this.name = name;
        this.startRentingDate = startRentingDate;
        this.endRentingDate = endRentingDate;
        this.imageData = imageData;
    }
}

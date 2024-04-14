package soa.group11.rentalService.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BikeDto {
    private String id;
    private String requestId;
    private String imageData;
    private String name;
    private String startRentingDate;
    private String endRentingDate;
    private String personOfContact;
    private String requestStatus;
    private String approvalStatus;
    private boolean rentalPeriodOver;
}

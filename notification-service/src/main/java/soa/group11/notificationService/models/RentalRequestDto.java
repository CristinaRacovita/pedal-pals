package soa.group11.notificationService.models;

import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class RentalRequestDto {
    private String bikeOwnerId;
    private String bikeRequesterId;
    private String bikeId;
    private String status;
    private String startDate;
    private String endDate;

    public RentalRequestDto() {
    }

    public RentalRequestDto(String bikeOwnerId, String bikeRequesterId, String bikeId, String status, String startDate,
            String endDate) {
        this.bikeOwnerId = bikeOwnerId;
        this.bikeRequesterId = bikeRequesterId;
        this.bikeId = bikeId;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        if (status.equals("cancelled")) {
            return "Request to rent bike " + bikeId + " for period " + startDate + " - " + endDate
                    + " has been cancelled by user " + bikeRequesterId + ".";
        } else {
            return "Request to rent bike " + bikeId + " for period " + startDate + " - " + endDate
                    + " has been sent by user " + bikeRequesterId + ".";
        }
    }
}
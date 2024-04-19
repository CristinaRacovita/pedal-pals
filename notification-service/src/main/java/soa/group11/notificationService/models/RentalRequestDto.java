package soa.group11.notificationService.models;

import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class RentalRequestDto {
    private int bikeOwnerId;
    private int bikeRequesterId;
    private String bikeId;
    private String status;
    private String startDate;
    private String endDate;

    public RentalRequestDto() {
    }

    public RentalRequestDto(int bikeOwnerId, int bikeRequesterId, String bikeId, String status, String startDate,
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
        if (this.status.equals("cancelled")) {
            return "User " + bikeRequesterId + " has cancelled the request to rent bike " + bikeId + " for period "
                    + startDate + " - " + endDate;
        } else {
            return "User " + bikeRequesterId + " has sent the request to rent bike " + bikeId + " for period "
                    + startDate + " - " + endDate;
        }
    }
}
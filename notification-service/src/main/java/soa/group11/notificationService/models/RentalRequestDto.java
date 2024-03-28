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

    public RentalRequestDto() {
    }

    public RentalRequestDto(String bikeOwnerId, String bikeRequesterId, String bikeId, String status) {
        this.bikeOwnerId = bikeOwnerId;
        this.bikeRequesterId = bikeRequesterId;
        this.bikeId = bikeId;
        this.status = status;
    }

    @Override
    public String toString() {
        if (status.equals("cancelled")) {
            return "Requester " + bikeRequesterId + " cancels the request for bike " + bikeId + " owned by "
                    + bikeOwnerId;
        } else {
            return "Bike owner " + bikeOwnerId + " received a request from " + bikeRequesterId + " for bike " + bikeId;
        }
    }
}
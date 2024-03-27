package main.java.soa.group11.notificationService.models;

import lombok.Setter;
import lombok.Getter;

@Getter
@Setter
public class BikeRequest{
    private String bikeOwnerId;
    private String bikeRequesterId;
    private String bikeId;

    public BikeRequest() {
    }

    public BikeRequest(String bikeOwnerId, String bikeRequesterId, String bikeId) {
        this.bikeOwnerId = bikeOwnerId;
        this.bikeRequesterId = bikeRequesterId;
        this.bikeId = bikeId;
    }

    @Override
    public String toString() {
        return "BikeRequest [bikeOwnerId=" + bikeOwnerId + ", bikeRequesterId=" + bikeRequesterId + ", bikeId=" + bikeId
                + "]";
    }
}
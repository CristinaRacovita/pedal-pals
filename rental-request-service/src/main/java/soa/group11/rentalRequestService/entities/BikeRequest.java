package soa.group11.rentalRequestService.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "bike_requests")
public class BikeRequest {
    @Id
    private String id;
    private String bikeOwnerId;
    private String bikeRequesterId;
    private String bikeId;
    private String status;

    public BikeRequest(String bikeOwnerId, String bikeRequesterId, String bikeId, String status) {
        this.bikeOwnerId = bikeOwnerId;
        this.bikeRequesterId = bikeRequesterId;
        this.bikeId = bikeId;
        this.status = status;
    }

    public BikeRequest(String id, String bikeOwnerId, String bikeRequesterId, String bikeId, String status) {
        this.id = id;
        this.bikeOwnerId = bikeOwnerId;
        this.bikeRequesterId = bikeRequesterId;
        this.bikeId = bikeId;
        this.status = status;
    }
}

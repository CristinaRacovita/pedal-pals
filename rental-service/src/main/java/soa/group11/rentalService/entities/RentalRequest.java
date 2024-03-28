package soa.group11.rentalService.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "requests")
public class RentalRequest {
    @Id
    private String id;
    private String bikeOwnerId;
    private String bikeRequesterId;
    private String bikeId;
    private String status;

    public RentalRequest(String bikeOwnerId, String bikeRequesterId, String bikeId, String status) {
        this.bikeOwnerId = bikeOwnerId;
        this.bikeRequesterId = bikeRequesterId;
        this.bikeId = bikeId;
        this.status = status;
    }

    public RentalRequest(String id, String bikeOwnerId, String bikeRequesterId, String bikeId, String status) {
        this.id = id;
        this.bikeOwnerId = bikeOwnerId;
        this.bikeRequesterId = bikeRequesterId;
        this.bikeId = bikeId;
        this.status = status;
    }
}

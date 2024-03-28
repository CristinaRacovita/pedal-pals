package soa.group11.rentalService.models;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Validated
@NoArgsConstructor
public class RentalRequestDto {
    private String id;
    @NotNull
    private String bikeOwnerId;
    @NotNull
    private String bikeRequesterId;
    @NotNull
    private String bikeId;
    private String status;

    public RentalRequestDto(String id, String bikeOwnerId, String bikeRequesterId, String bikeId, String status) {
        this.id = id;
        this.bikeOwnerId = bikeOwnerId;
        this.bikeRequesterId = bikeRequesterId;
        this.bikeId = bikeId;
        this.status = status;
    }

    public RentalRequestDto(String bikeOwnerId, String bikeRequesterId, String bikeId, String status) {
        this.bikeOwnerId = bikeOwnerId;
        this.bikeRequesterId = bikeRequesterId;
        this.bikeId = bikeId;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Requested sent by " + bikeRequesterId + " to " + bikeOwnerId + " for bike " + bikeId + " with status "
                + status;
    }
}

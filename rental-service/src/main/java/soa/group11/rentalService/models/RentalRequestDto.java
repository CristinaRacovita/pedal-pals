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
    private int bikeOwnerId;
    private int bikeRequesterId;
    @NotNull
    private String bikeId;
    private String status;
    @NotNull
    private String startDate;
    @NotNull
    private String endDate;
    private String bikeRequesterName;
    private String bikeName;

    public RentalRequestDto(String id, int bikeOwnerId, int bikeRequesterId, String bikeId, String status,
            String startDate, String endDate, String bikeRequesterName, String bikeName) {
        this.id = id;
        this.bikeOwnerId = bikeOwnerId;
        this.bikeRequesterId = bikeRequesterId;
        this.bikeId = bikeId;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.bikeRequesterName = bikeRequesterName;
        this.bikeName = bikeName;
    }

    public RentalRequestDto(int bikeOwnerId, int bikeRequesterId, String bikeId, String status, String startDate,
            String endDate, String bikeRequesterName, String bikeName) {
        this.bikeOwnerId = bikeOwnerId;
        this.bikeRequesterId = bikeRequesterId;
        this.bikeId = bikeId;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
        this.bikeRequesterName = bikeRequesterName;
        this.bikeName = bikeName;
    }
    
    @Override
    public String toString() {
        return "Request sent by " + bikeRequesterId + " to " + bikeOwnerId + " for bike " + bikeId + " with status "
                + status + " for period " + startDate + " - " + endDate;
    }
}

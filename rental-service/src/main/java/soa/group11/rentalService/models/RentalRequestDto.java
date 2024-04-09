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
    @NotNull
    private String startDate;
    @NotNull
    private String endDate;

    public RentalRequestDto(String id, String bikeOwnerId, String bikeRequesterId, String bikeId, String status,
            String startDate, String endDate) {
        this.id = id;
        this.bikeOwnerId = bikeOwnerId;
        this.bikeRequesterId = bikeRequesterId;
        this.bikeId = bikeId;
        this.status = status;
        this.startDate = startDate;
        this.endDate = endDate;
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
        return "Request sent by " + bikeRequesterId + " to " + bikeOwnerId + " for bike " + bikeId + " with status "
                + status + " for period " + startDate + " - " + endDate;
    }
}

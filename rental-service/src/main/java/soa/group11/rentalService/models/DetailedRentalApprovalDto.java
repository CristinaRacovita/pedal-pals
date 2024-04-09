package soa.group11.rentalService.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DetailedRentalApprovalDto {
    private String requesterId;
    private String bikeId;
    private String approvalStatus;
    private String details;

    public DetailedRentalApprovalDto(String requesterId, String bikeId, String approvalStatus, String details) {
        this.requesterId = requesterId;
        this.bikeId = bikeId;
        this.approvalStatus = approvalStatus;
        this.details = details;
    }

    @Override
    public String toString() {
        return "Request was " + approvalStatus + " with the follwing details: " + details;
    }
}

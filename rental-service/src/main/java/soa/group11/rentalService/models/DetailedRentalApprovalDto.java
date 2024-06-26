package soa.group11.rentalService.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DetailedRentalApprovalDto {
    private int requesterId;
    private String bikeId;
    private String approvalStatus;
    private String details;
    private String startDate;
    private String endDate;
    private String bikeName;

    public DetailedRentalApprovalDto(int requesterId, String bikeId, String approvalStatus, String details,
            String startDate, String endDate, String bikeName) {
        this.requesterId = requesterId;
        this.bikeId = bikeId;
        this.approvalStatus = approvalStatus;
        this.details = details;
        this.startDate = startDate;
        this.endDate = endDate;
        this.bikeName = bikeName;
    }

    @Override
    public String toString() {
        return "Request was " + approvalStatus + " with the follwing details: " + details;
    }
}

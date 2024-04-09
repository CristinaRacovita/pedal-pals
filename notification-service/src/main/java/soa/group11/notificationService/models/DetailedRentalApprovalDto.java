package soa.group11.notificationService.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailedRentalApprovalDto {
    private String requesterId;
    private String bikeId;
    private String approvalStatus;
    private String details;

    public DetailedRentalApprovalDto() {

    }

    public DetailedRentalApprovalDto(String requesterId, String bikeId, String approvalStatus, String details) {
        this.requesterId = requesterId;
        this.bikeId = bikeId;
        this.approvalStatus = approvalStatus;
        this.details = details;
    }

    @Override
    public String toString() {
        if (this.approvalStatus.equals("approved")) {
            return "Request for bike " + bikeId + " has been approved with the following details: " + details;
        } else {
            return "Request for bike " + bikeId + " has been declined with the following details: " + details;
        }
    }
}

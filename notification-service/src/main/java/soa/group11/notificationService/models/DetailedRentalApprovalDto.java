package soa.group11.notificationService.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DetailedRentalApprovalDto {
    private int requesterId;
    private String bikeId;
    private String approvalStatus;
    private String details;
    private String startDate;
    private String endDate;
    private String bikeName;

    public DetailedRentalApprovalDto() {

    }

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
        if (this.approvalStatus.equals("approved")) {
            return "Request for bike '" + bikeName + "' has been approved with the following details: " + details;
        } else {
            return "Request for bike '" + bikeName + "' has been declined with the following details: " + details;
        }
    }
}

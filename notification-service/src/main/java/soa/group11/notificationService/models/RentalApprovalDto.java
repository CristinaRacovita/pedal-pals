package soa.group11.notificationService.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalApprovalDto {
    private String id;
    private String requestId;
    private String approvalStatus;
    private String details;

    public RentalApprovalDto() {

    }

    public RentalApprovalDto(String requestId, String approvalStatus, String details) {
        this.requestId = requestId;
        this.approvalStatus = approvalStatus;
        this.details = details;
    }

    public RentalApprovalDto(String id, String requestId, String approvalStatus, String details) {
        this.id = id;
        this.requestId = requestId;
        this.approvalStatus = approvalStatus;
        this.details = details;
    }

    @Override
    public String toString() {
        return "Request " + requestId + " was " + approvalStatus + " with the follwing details: " + details;
    }
}

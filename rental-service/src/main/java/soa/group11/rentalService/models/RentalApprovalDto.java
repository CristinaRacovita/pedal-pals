package soa.group11.rentalService.models;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Validated
@NoArgsConstructor
public class RentalApprovalDto {
    private String id;
    @NotNull
    private String requestId;
    @NotNull
    private String approvalStatus;
    @NotNull
    private String details;

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
}

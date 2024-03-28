package soa.group11.rentalService.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Setter;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "approvals")
public class RentalApproval {
    @Id
    private String id;
    private String requestId;
    private String approvalStatus;
    private String details;

    public RentalApproval(String requestId, String approvalStatus, String details) {
        this.requestId = requestId;
        this.approvalStatus = approvalStatus;
        this.details = details;
    }

    public RentalApproval(String id, String requestId, String approvalStatus, String details) {
        this.id = id;
        this.requestId = requestId;
        this.approvalStatus = approvalStatus;
        this.details = details;
    }
}

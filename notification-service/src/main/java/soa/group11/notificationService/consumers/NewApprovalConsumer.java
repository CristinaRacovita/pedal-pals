package soa.group11.notificationService.consumers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import soa.group11.notificationService.entities.ApprovalNotification;
import soa.group11.notificationService.models.DetailedRentalApprovalDto;
import soa.group11.notificationService.repositories.ApprovalNotificationRepository;

@Component
public class NewApprovalConsumer {
    @Autowired
    private ApprovalNotificationRepository approvalNotificationRepository;

    @JmsListener(destination = "${queue.approval}")
    public void receiveRequest(String approvalJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            DetailedRentalApprovalDto detailedRentalApproval = objectMapper.readValue(approvalJson,
                    DetailedRentalApprovalDto.class);

            System.out.println(detailedRentalApproval.toString());
            approvalNotificationRepository.save(new ApprovalNotification(detailedRentalApproval.getRequesterId(),
                    detailedRentalApproval.toString()));
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
    }
}

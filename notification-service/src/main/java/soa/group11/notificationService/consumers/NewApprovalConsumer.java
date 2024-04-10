package soa.group11.notificationService.consumers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import soa.group11.notificationService.entities.Notification;
import soa.group11.notificationService.models.DetailedRentalApprovalDto;
import soa.group11.notificationService.repositories.NotificationRepository;

@Component
public class NewApprovalConsumer {
    @Autowired
    private NotificationRepository notificationRepository;

    @JmsListener(destination = "${queue.approval}")
    public void receiveRequest(String approvalJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            DetailedRentalApprovalDto detailedRentalApproval = objectMapper.readValue(approvalJson,
                    DetailedRentalApprovalDto.class);

            System.out.println(detailedRentalApproval.toString());
            String notificationType;
            String endRentalDate;

            if (detailedRentalApproval.toString().contains("has been approved with the following details")) {
                notificationType = "approved_request";
                endRentalDate = detailedRentalApproval.getEndDate();
            } else {
                notificationType = "declined_request";
                endRentalDate = null;
            }

            notificationRepository.save(new Notification(detailedRentalApproval.getRequesterId(), notificationType,
                    detailedRentalApproval.toString(), endRentalDate));
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
    }
}

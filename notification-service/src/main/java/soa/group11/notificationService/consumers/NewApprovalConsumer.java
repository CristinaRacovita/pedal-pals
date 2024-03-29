package soa.group11.notificationService.consumers;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import soa.group11.notificationService.models.RentalApprovalDto;

@Component
public class NewApprovalConsumer {
    @JmsListener(destination = "${queue.approval}")
    public void receiveRequest(String approvalJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            RentalApprovalDto rentalApproval = objectMapper.readValue(approvalJson, RentalApprovalDto.class);

            System.out.println(rentalApproval.toString());
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
    }
}

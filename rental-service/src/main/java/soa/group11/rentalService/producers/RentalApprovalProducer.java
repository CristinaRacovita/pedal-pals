package soa.group11.rentalService.producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import soa.group11.rentalService.models.RentalApprovalDto;

@Component
public class RentalApprovalProducer {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${queue.approval}")
    private String notificationQueue;

    public void sendApprovalStatus(RentalApprovalDto rentalApprovalDto) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String rentalApprovalJson = objectMapper.writeValueAsString(rentalApprovalDto);
            jmsTemplate.convertAndSend(notificationQueue, rentalApprovalJson);

            System.out.println("Sent approval: " + rentalApprovalDto.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

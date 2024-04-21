package soa.group11.rentalService.producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import soa.group11.rentalService.entities.RentalRequest;
import soa.group11.rentalService.models.DetailedRentalApprovalDto;
import soa.group11.rentalService.models.RentalApprovalDto;

@Component
public class RentalApprovalProducer {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${queue.approval}")
    private String notificationQueue;

    public void sendApprovalStatus(RentalApprovalDto rentalApprovalDto, RentalRequest rentalRequest) {
        DetailedRentalApprovalDto detailedRentalApprovalDto = new DetailedRentalApprovalDto(
                rentalRequest.getBikeRequesterId(), rentalRequest.getBikeId(), rentalApprovalDto.getApprovalStatus(),
                rentalApprovalDto.getDetails(), rentalRequest.getStringStartDate(), rentalRequest.getStringEndDate(),
                null);

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(
                    "http://bike-management.pedalpals:8082/bikes/" + detailedRentalApprovalDto.getBikeId() + "/names", String.class);
            detailedRentalApprovalDto.setBikeName(response.getBody());
        } catch (Exception e) {
            System.out.println("Bike not found...");
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String rentalApprovalJson = objectMapper.writeValueAsString(detailedRentalApprovalDto);
            jmsTemplate.convertAndSend(notificationQueue, rentalApprovalJson);

            System.out.println("Sent approval: " + detailedRentalApprovalDto.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

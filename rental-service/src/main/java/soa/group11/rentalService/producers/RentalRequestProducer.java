package soa.group11.rentalService.producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import soa.group11.rentalService.models.RentalRequestDto;

import org.springframework.jms.core.JmsTemplate;

@Component
public class RentalRequestProducer {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${queue.request}")
    private String notificationQueue;

    public void sendRequest(RentalRequestDto rentalRequestDto) {

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(
                    "http://localhost:8082/bikes/" + rentalRequestDto.getBikeId() + "/names", String.class);
            rentalRequestDto.setBikeName(response.getBody());
        } catch (Exception e) {
            System.out.println("Bike not found...");
        }

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(
                    "http://localhost:8080/users/username/" + rentalRequestDto.getBikeRequesterId(),
                    String.class);
            rentalRequestDto.setBikeRequesterName(response.getBody());
        } catch (Exception e) {
            System.out.println("Requester not found...");
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String rentalRequestJson = objectMapper.writeValueAsString(rentalRequestDto);
            jmsTemplate.convertAndSend(notificationQueue, rentalRequestJson);

            System.out.println("Sent request: " + rentalRequestDto.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}

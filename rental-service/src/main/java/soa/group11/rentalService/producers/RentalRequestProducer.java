package soa.group11.rentalService.producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
            ObjectMapper objectMapper = new ObjectMapper();
            String rentalRequestJson = objectMapper.writeValueAsString(rentalRequestDto);
            jmsTemplate.convertAndSend(notificationQueue, rentalRequestJson);

            System.out.println("Sent request: " + rentalRequestDto.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

package soa.group11.rentalRequestService.producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import soa.group11.rentalRequestService.models.BikeRequestDto;

import org.springframework.jms.core.JmsTemplate;

@Component
public class BikeRequestProducer {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${queue.request}")
    private String notificationQueue;

    public void sendRequest(BikeRequestDto bikeRequestDto) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String bikeRequestJson = objectMapper.writeValueAsString(bikeRequestDto);
            jmsTemplate.convertAndSend(notificationQueue, bikeRequestJson);

            System.out.println("Sent request: " + bikeRequestDto.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

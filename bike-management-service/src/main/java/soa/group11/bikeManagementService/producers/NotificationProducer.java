package soa.group11.bikeManagementService.producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import soa.group11.bikeManagementService.models.BikeDto;

@Component
public class NotificationProducer {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${queue.notification}") 
    private String notificationQueue;

    public void sendMessage(BikeDto bike) { 
        try {
            ObjectMapper mapper = new ObjectMapper();
            String bikeJson = mapper.writeValueAsString(bike);
            jmsTemplate.convertAndSend(notificationQueue, bikeJson);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }  
    } 
}

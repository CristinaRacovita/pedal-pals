package soa.group11.bikeManagementService.producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import soa.group11.bikeManagementService.models.BikeDto;

@Component
public class NewSubscriptionProducer {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${queue.new-subscription}") 
    private String newSubscriptionQueue;

    public void sendMessage(BikeDto bike) { 
        try {
            ObjectMapper mapper = new ObjectMapper();
            String bikeJson = mapper.writeValueAsString(bike);
            jmsTemplate.convertAndSend(newSubscriptionQueue, bikeJson);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }  
    } 
}

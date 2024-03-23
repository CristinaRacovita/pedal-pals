package soa.group11.notificationService.consumers;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import soa.group11.notificationService.entities.Subscription;

@Component
public class NewSubscriptionConsumer {    
    @JmsListener(destination = "${queue.new-subscription}")
    public void receiveMessage(String bikeJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Subscription bikeSubscription;
            bikeSubscription = objectMapper.readValue(bikeJson, Subscription.class);
            System.out.println("Received message: " + bikeSubscription.getBrand());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

package soa.group11.notificationService.consumers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import soa.group11.notificationService.entities.BikeSubscription;
import soa.group11.notificationService.repositories.BikeSubscriptionRepository;

@Component
public class NewSubscriptionConsumer {
    @Autowired
    private BikeSubscriptionRepository subscriptionRepository;

    @JmsListener(destination = "${queue.new-subscription}")
    public void receiveMessage(String bikeJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            BikeSubscription bikeSubscription;
            bikeSubscription = objectMapper.readValue(bikeJson, BikeSubscription.class);
            subscriptionRepository.save(bikeSubscription);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

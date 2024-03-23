package soa.group11.notificationService.consumers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import soa.group11.notificationService.entities.BikeSubscription;
import soa.group11.notificationService.repositories.BikeSubscriptionRepository;

@Component
public class NotificationConsumer {    
    @Autowired
    private BikeSubscriptionRepository subscriptionRepository;
    
    @JmsListener(destination = "${queue.notification}")
    public void receiveMessage(String bikeJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            BikeSubscription bikeSubscription = objectMapper.readValue(bikeJson, BikeSubscription.class);
            List<BikeSubscription> subscriptions = subscriptionRepository.getSubscriptionsLike(bikeSubscription.getBrand(), bikeSubscription.getNumberOfGears());
            
            for (BikeSubscription subscription : subscriptions) {
                System.console().printf("User %d should be notified!!! \n", subscription.getUserId());
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

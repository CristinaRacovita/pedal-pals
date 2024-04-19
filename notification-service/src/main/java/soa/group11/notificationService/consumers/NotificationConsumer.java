package soa.group11.notificationService.consumers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import soa.group11.notificationService.entities.Notification;
import soa.group11.notificationService.entities.BikeSubscription;
import soa.group11.notificationService.repositories.NotificationRepository;
import soa.group11.notificationService.repositories.BikeSubscriptionRepository;

@Component
public class NotificationConsumer {
    @Autowired
    private BikeSubscriptionRepository subscriptionRepository;

    @Autowired
    private NotificationRepository bikeNotificationRepository;

    @JmsListener(destination = "${queue.notification}")
    public void receiveMessage(String bikeJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            BikeSubscription newBike = objectMapper.readValue(bikeJson, BikeSubscription.class);

            List<BikeSubscription> subscriptions = subscriptionRepository.getSubscriptionsLike(newBike.getWheelSize(),
                    newBike.getNumberOfGears(), newBike.getStartDate(), newBike.getEndDate(), newBike.getBrand(),
                    newBike.getType(), newBike.getUsage());

            for (BikeSubscription subscription : subscriptions) {
                int notifiedUserId = subscription.getUserId();

                List<Notification> storedBikeNotifications = bikeNotificationRepository
                        .findNotificationsLike(notifiedUserId, "bike_notification", newBike.toString());

                if (storedBikeNotifications.isEmpty() || storedBikeNotifications == null) {
                    bikeNotificationRepository
                            .save(new Notification(notifiedUserId, "bike_notification", newBike.toString(), null));
                    System.console().printf("User %s should be notified!!! \n", notifiedUserId);
                }
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

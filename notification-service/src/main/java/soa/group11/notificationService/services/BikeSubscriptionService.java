package soa.group11.notificationService.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soa.group11.notificationService.entities.BikeSubscription;
import soa.group11.notificationService.repositories.BikeSubscriptionRepository;

@Service
public class BikeSubscriptionService {
    @Autowired
    private BikeSubscriptionRepository subscriptionRepository;

    public List<BikeSubscription> getSubscriptionsByUserId(int userId) {
        List<BikeSubscription> subscriptions = subscriptionRepository.findByUserId(userId);
        return subscriptions;
    }
}

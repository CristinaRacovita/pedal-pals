package soa.group11.notificationService.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soa.group11.notificationService.entities.Subscription;
import soa.group11.notificationService.repositories.SubscriptionRepository;

@Service
public class SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

     public List<Subscription> getSubscriptionsByUserId(int userId) {
        List<Subscription> subscriptions = subscriptionRepository.findByUserId(userId);
        return subscriptions;
    }
}

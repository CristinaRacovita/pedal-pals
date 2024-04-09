package soa.group11.notificationService.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import soa.group11.notificationService.entities.BikeSubscription;
import soa.group11.notificationService.services.BikeSubscriptionService;

@RestController
public class BikeSubscriptionController {
    @Autowired
    private BikeSubscriptionService subscriptionService;

    @GetMapping("/subscription/{userId}")
    public List<BikeSubscription> getSubscriptionsByUserId(@PathVariable(value = "userId") int userId) {
        return subscriptionService.getSubscriptionsByUserId(userId);
    }
}
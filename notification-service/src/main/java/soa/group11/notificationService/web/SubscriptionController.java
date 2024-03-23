package soa.group11.notificationService.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import soa.group11.notificationService.entities.Subscription;
import soa.group11.notificationService.services.SubscriptionService;

@RestController
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("/notification/{userId}")
    public List<Subscription> getSubscriptionsByUserId(@PathVariable(value="userId") int userId) {
        return subscriptionService.getSubscriptionsByUserId(userId);
    }
}
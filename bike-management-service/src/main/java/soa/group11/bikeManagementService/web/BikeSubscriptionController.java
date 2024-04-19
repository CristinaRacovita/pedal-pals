package soa.group11.bikeManagementService.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import soa.group11.bikeManagementService.models.BikeSubscriptionDto;
import soa.group11.bikeManagementService.producers.NewSubscriptionProducer;

@RestController
@EnableMongoRepositories
public class BikeSubscriptionController {
    @Autowired
    private NewSubscriptionProducer newSubscriptionProducer;

    @PostMapping("/bike-subscriptions")
    public void createNewBikeSubscription(@RequestBody BikeSubscriptionDto bikeSubscriptionDto) {
        newSubscriptionProducer.sendMessage(bikeSubscriptionDto);
    }
}

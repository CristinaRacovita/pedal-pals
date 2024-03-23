package soa.group11.bikeManagementService.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import soa.group11.bikeManagementService.models.BikeDto;
import soa.group11.bikeManagementService.producers.NewSubscriptionProducer;
import soa.group11.bikeManagementService.services.BikeService;

@RestController
public class BikeController {
    @Autowired private BikeService bikeService;
    @Autowired private NewSubscriptionProducer newSubscriptionProducer;

    @GetMapping("/{userId}")
    public List<BikeDto> getBikesForUser(@PathVariable(value="userId") int userId) {
        return bikeService.getBikesByUserId(userId);
    }

    @PostMapping("/bike-subscription")
    public void createNewBikeSubscription(@RequestBody BikeDto bike) {
        newSubscriptionProducer.sendMessage(bike);
    }
}

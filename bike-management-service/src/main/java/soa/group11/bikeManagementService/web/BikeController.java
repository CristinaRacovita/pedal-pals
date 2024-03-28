package soa.group11.bikeManagementService.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import soa.group11.bikeManagementService.models.BikeDto;
import soa.group11.bikeManagementService.producers.NotificationProducer;
import soa.group11.bikeManagementService.services.BikeService;

@RestController
public class BikeController {
    @Autowired private BikeService bikeService;
    @Autowired private NotificationProducer notificationProducer;

    @PostMapping("/bike")
    public void addNewBike(@RequestBody BikeDto bike) {
        bikeService.addBike(bike);
        notificationProducer.sendMessage(bike);
    }
}

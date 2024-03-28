package soa.group11.bikeManagementService.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import soa.group11.bikeManagementService.models.AccessoryDto;
import soa.group11.bikeManagementService.services.AccessoryService;

@RestController

public class AccessoryController {
    @Autowired
    private AccessoryService accessoryService;

    @PostMapping("/accessory")
    public void add(@RequestBody AccessoryDto accessoryDto) {
        accessoryService.addAccessoryForBike(accessoryDto);
    }
}

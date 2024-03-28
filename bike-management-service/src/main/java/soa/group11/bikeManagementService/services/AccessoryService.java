package soa.group11.bikeManagementService.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soa.group11.bikeManagementService.entities.Accessory;
import soa.group11.bikeManagementService.entities.Bike;
import soa.group11.bikeManagementService.models.AccessoryDto;
import soa.group11.bikeManagementService.repositories.AccessoryRepository;
import soa.group11.bikeManagementService.repositories.BikeRepository;

@Service
public class AccessoryService {
    @Autowired
    private AccessoryRepository accessoryRepository;

    @Autowired
    private BikeRepository bikeRepository;

    public void addAccessoryForBike(AccessoryDto accessoryDto) {
        Optional<Bike> bike = bikeRepository.findById(accessoryDto.getBikeId());
        if (!bike.isPresent()) {
            System.out.println("Bike not found with id: " + accessoryDto.getBikeId());
            return;
        }

        Accessory accessory = toAccessory(accessoryDto);
        if(accessory == null){
            return;
        }

        this.accessoryRepository.save(accessory);
        if (bike.get().getAccessories() == null) {
            bike.get().setAccessories(new ArrayList<Accessory>());
        }

        bike.get().getAccessories().add(accessory);
        Bike bikeToStore = bike.get();
        if(bikeToStore == null) {
            return;
        }
        
        bikeRepository.save(bikeToStore);

    }

    private Accessory toAccessory(AccessoryDto accessoryDto) {
        return new Accessory(accessoryDto.getName(), accessoryDto.getBrand());
    }
}

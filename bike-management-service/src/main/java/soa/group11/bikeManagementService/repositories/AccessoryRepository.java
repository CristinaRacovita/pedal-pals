package soa.group11.bikeManagementService.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import soa.group11.bikeManagementService.entities.Accessory;

public interface AccessoryRepository extends MongoRepository<Accessory, String> {
    
}

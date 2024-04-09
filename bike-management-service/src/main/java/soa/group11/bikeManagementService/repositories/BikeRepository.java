package soa.group11.bikeManagementService.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import soa.group11.bikeManagementService.entities.Bike;

public interface BikeRepository extends MongoRepository<Bike, String> {
    List<Bike> findByUserId(int userId);
}

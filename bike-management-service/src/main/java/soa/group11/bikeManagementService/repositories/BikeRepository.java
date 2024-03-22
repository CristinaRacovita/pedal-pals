package soa.group11.bikeManagementService.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.Repository;

import soa.group11.bikeManagementService.entities.Bike;

public interface BikeRepository extends Repository<Bike, UUID> {
    List<Bike> getBikesByUserId(int userId);
}

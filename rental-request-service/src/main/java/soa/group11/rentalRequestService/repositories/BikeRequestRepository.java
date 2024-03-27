package soa.group11.rentalRequestService.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import soa.group11.rentalRequestService.entities.BikeRequest;

@Repository
public interface BikeRequestRepository extends MongoRepository<BikeRequest, String> {
    @Query(value = "{ 'requesterId' : ?0 }")
    List<BikeRequest> findAllByRequesterId(String requesterId);

    @Query(value = "{ 'bikeId' : ?0 }")
    List<BikeRequest> findAllByBikeId(String bikeId);

    @Query(value = "{ 'bikeId' : ?0, 'bikeRequesterId' : ?1 }")
    List<BikeRequest> findAllByBikeIdAndBikeRequesterId(String bikeId, String bikeRequesterId);
}

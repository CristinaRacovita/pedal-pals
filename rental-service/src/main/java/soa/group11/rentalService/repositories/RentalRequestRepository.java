package soa.group11.rentalService.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import soa.group11.rentalService.entities.RentalRequest;

@Repository
public interface RentalRequestRepository extends MongoRepository<RentalRequest, String> {
    @Query(value = "{ 'requesterId' : ?0 }")
    List<RentalRequest> findAllByRequesterId(String requesterId);

    @Query(value = "{ 'bikeId' : ?0 }")
    List<RentalRequest> findAllByBikeId(String bikeId);

    @Query(value = "{ 'bikeId' : ?0, 'bikeRequesterId' : ?1 }")
    List<RentalRequest> findAllByBikeIdAndBikeRequesterId(String bikeId, String bikeRequesterId);
}

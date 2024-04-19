package soa.group11.rentalService.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import soa.group11.rentalService.entities.RentalRequest;

@Repository
public interface RentalRequestRepository extends MongoRepository<RentalRequest, String> {
    @Query(value = "{ 'requesterId' : ?0 }")
    List<RentalRequest> findAllByRequesterId(int requesterId);

    @Query(value = "{ 'bikeId' : ?0 }")
    List<RentalRequest> findAllByBikeId(String bikeId);

    @Query(value = "{ 'bikeId' : ?0, 'bikeRequesterId' : ?1 }")
    List<RentalRequest> findAllByBikeIdAndBikeRequesterId(String bikeId, int bikeRequesterId);

    @Query(value = "{ 'bikeOwnerId' : ?0 }")
    List<RentalRequest> findAllByBikeOwnerId(int bikeOwnerId);

    @Query(value = "{ 'bikeRequesterId' : ?0 }")
    List<RentalRequest> findAllByBikeRequesterId(int bikeRequesterId);
}

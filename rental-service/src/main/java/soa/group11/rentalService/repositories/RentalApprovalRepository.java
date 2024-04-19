package soa.group11.rentalService.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import soa.group11.rentalService.entities.RentalApproval;

@Repository
public interface RentalApprovalRepository extends MongoRepository<RentalApproval, String> {
    @Query(value = "{ 'requestId' : ?0 }")
    List<RentalApproval> findAllByRequestId(String requestId);

}

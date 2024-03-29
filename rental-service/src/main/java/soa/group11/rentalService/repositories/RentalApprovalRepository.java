package soa.group11.rentalService.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import soa.group11.rentalService.entities.RentalApproval;

@Repository
public interface RentalApprovalRepository extends MongoRepository<RentalApproval, String> {

}

package soa.group11.notificationService.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import soa.group11.notificationService.entities.BikeSubscription;

@Repository
public interface BikeSubscriptionRepository extends MongoRepository<BikeSubscription, String> {
    List<BikeSubscription> findByUserId(int userId);

    @Query(value = "{'wheelSize': ?0, 'numberOfGears': ?1, 'brand': ?4, 'type': ?5, 'usage': ?6, 'startDate' : {$lte: ?2}, 'endDate' : {$gte: ?3}}")
    List<BikeSubscription> getSubscriptionsLike(int wheelSize, int numberOfGears, String startDate,
            String endDate, String brand, String type, String usage);
}

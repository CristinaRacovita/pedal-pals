package soa.group11.notificationService.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import soa.group11.notificationService.entities.BikeSubscription;

@Repository
public interface BikeSubscriptionRepository extends MongoRepository<BikeSubscription, String> {
    List<BikeSubscription> findByUserId(int userId);

    @Query(value = "{'brand': ?0, 'numberOfGears': ?1}")
    List<BikeSubscription> getSubscriptionsLike(String brand, int numberOfGears);
}

package soa.group11.feedbackService.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import soa.group11.feedbackService.entities.Review;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {
    @Query(value = "{ 'bikeId' : ?0 }")
    List<Review> findByBikeId(String bikeId);

    List<Review> findByBikeIdIn(List<String> bikeIds);
}
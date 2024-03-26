package soa.group11.feedbackService.repositories;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import soa.group11.feedbackService.entities.Feedback;

@Repository
public interface FeedbackRepository extends MongoRepository<Feedback, String> {
    @Query(value="{ 'bikeId' : ?0 }")
    List<Feedback> findFeedbacksByBikeId(String bikeId);

    List<Feedback> findByBikeIdIn(List<String> bikeIds);
}
package soa.group11.feedbackService.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import soa.group11.feedbackService.entities.Feedback;

@Repository
public interface FeedbackRepository extends MongoRepository<Feedback, String> {
}
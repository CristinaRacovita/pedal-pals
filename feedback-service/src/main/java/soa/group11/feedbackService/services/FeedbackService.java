package soa.group11.feedbackService.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soa.group11.feedbackService.entities.Feedback;
import soa.group11.feedbackService.models.FeedbackDto;
import soa.group11.feedbackService.repositories.FeedbackRepository;

@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public List<FeedbackDto> getAllFeedbacks() {
        return feedbackRepository.findAll().stream().map(feedback -> toFeedbackDto(feedback))
                .collect(Collectors.toList());
    }

    public List<FeedbackDto> getFeedbacksByBikeIds(List<String> bikeId) {
        return feedbackRepository.findByBikeIdIn(bikeId).stream().map(feedback -> toFeedbackDto(feedback))
                .collect(Collectors.toList());
    }

    public boolean deleteFeedback(String id) {
        if (this.feedbackRepository.existsById(id)) {
            this.feedbackRepository.deleteById(id);
            return true;
        }

        return false;
    }

    public FeedbackDto updateFeedback(String id, FeedbackDto feedbackDto) throws Exception {
        // make sure the object matches the path variable
        feedbackDto.setId(id);
        Feedback feedback = this.feedbackRepository.findById(id).orElse(null);

        if (feedback != null) {
            // only the review and the number of stars can be updated
            feedback.setNumberOfStars(feedbackDto.getNumberOfStars());
            feedback.setReview(feedbackDto.getReview());

            feedbackRepository.save(feedback);

            return toFeedbackDto(feedback);
        } else {
            throw new Exception("Feedback with ID " + id + " not found");
        }
    }

    public void addFeedback(FeedbackDto feedbackDto) {
        feedbackRepository.save(toFeedback(feedbackDto));
    }

    private FeedbackDto toFeedbackDto(Feedback feedback) {
        return new FeedbackDto(feedback.getId(), feedback.getBikeId(), feedback.getReviewerId(),
                feedback.getNumberOfStars(), feedback.getReview());
    }

    private Feedback toFeedback(FeedbackDto feedbackDto) {
        return new Feedback(feedbackDto.getBikeId(), feedbackDto.getReviewerId(), feedbackDto.getNumberOfStars(),
                feedbackDto.getReview());
    }

}

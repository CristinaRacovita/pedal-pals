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

    public void deleteFeedback(String id){
        this.feedbackRepository.deleteById(id);
    }

    public FeedbackDto updateFeedback(String id, FeedbackDto feedbackDto) throws Exception{
        // make sure the object matches the path variable
        feedbackDto.setId(id);
        Feedback feedback = this.feedbackRepository.findById(id).orElse(null);

        if (feedback != null){
            feedback.setBikeId(feedbackDto.getBikeId());
            feedback.setNumberOfStars(feedbackDto.getNumberOfStars());
            feedback.setReviewerId(feedbackDto.getReviewerId());
            feedback.setReview(feedbackDto.getReview());
            
            feedbackRepository.save(feedback);

            return toFeedbackDto(feedback);
        }else{
            throw new Exception("Feedback with ID " + id + " not found");
        }
    }

    private FeedbackDto toFeedbackDto(Feedback feedback) {
        return new FeedbackDto(feedback.getId(), feedback.getBikeId(), feedback.getReviewerId(), feedback.getNumberOfStars(), feedback.getReview());
    }

    public void addFeedback(FeedbackDto feedbackDto){
        feedbackRepository.save(toFeedback(feedbackDto));
    }

    private Feedback toFeedback(FeedbackDto feedbackDto){
        return new Feedback(feedbackDto.getBikeId(), feedbackDto.getReviewerId(), feedbackDto.getNumberOfStars(), feedbackDto.getReview());
    }

}

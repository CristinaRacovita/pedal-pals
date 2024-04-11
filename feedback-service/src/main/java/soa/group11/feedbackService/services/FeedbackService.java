package soa.group11.feedbackService.services;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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

    public List<FeedbackDto> getAllFeedback() {
        return feedbackRepository.findAll().stream().map(feedback -> toFeedbackDto(feedback))
                .collect(Collectors.toList());
    }

    public List<FeedbackDto> getFeedbackByBikeId(String bikeId) {
        List<FeedbackDto> feedbackObjects = feedbackRepository.findByBikeId(bikeId).stream()
                .map(feedback -> toFeedbackDto(feedback))
                .collect(Collectors.toList());

        feedbackObjects.sort(Comparator.comparing(FeedbackDto::getReviewDate).reversed());

        return feedbackObjects;
    }

    public Double getAverageScoreForBike(String bikeId) {
        Double sum = 0.0;
        List<Feedback> feedback = feedbackRepository.findByBikeId(bikeId);
        for (Feedback feedbackDto : feedback) {
            sum += feedbackDto.getNumberOfStars();
        }

        if (sum == 0.0) {
            return 0.0;
        }

        return sum / feedback.size();
    }

    public boolean deleteFeedback(String id) {
        if (!this.feedbackRepository.existsById(id)) {
            return false;
        }

        this.feedbackRepository.deleteById(id);
        return true;
    }

    public FeedbackDto updateFeedback(String id, FeedbackDto feedbackDto) throws NotFoundException {
        feedbackDto.setId(id);
        Feedback feedback = this.feedbackRepository.findById(id).orElse(null);

        if (feedback == null) {
            throw new NotFoundException();
        }

        feedback.setNumberOfStars(feedbackDto.getNumberOfStars());
        feedback.setReview(feedbackDto.getReview());

        feedbackRepository.save(feedback);

        return toFeedbackDto(feedback);
    }

    public void addFeedback(FeedbackDto feedbackDto) {
        feedbackRepository.save(toFeedback(feedbackDto));
    }

    private FeedbackDto toFeedbackDto(Feedback feedback) {
        return new FeedbackDto(feedback.getId(), feedback.getBikeId(), feedback.getTitle(), feedback.getReviewerId(),
                feedback.getNumberOfStars(), feedback.getReview(), feedback.getDate());
    }

    private Feedback toFeedback(FeedbackDto feedbackDto) {
        return new Feedback(feedbackDto.getBikeId(), feedbackDto.getTitle(), feedbackDto.getReviewerId(),
                feedbackDto.getNumberOfStars(),
                feedbackDto.getReview());
    }

}

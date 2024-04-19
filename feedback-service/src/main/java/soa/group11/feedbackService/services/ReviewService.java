package soa.group11.feedbackService.services;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import soa.group11.feedbackService.entities.Review;
import soa.group11.feedbackService.models.ReviewDto;
import soa.group11.feedbackService.repositories.ReviewRepository;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public List<ReviewDto> getReviews() {
        return reviewRepository.findAll().stream().map(review -> toReviewDto(review))
                .collect(Collectors.toList());
    }

    public List<ReviewDto> getReviewsByBikeId(String bikeId) {
        List<ReviewDto> feedbackObjects = reviewRepository.findByBikeId(bikeId).stream()
                .map(review -> toReviewDto(review))
                .collect(Collectors.toList());

        feedbackObjects.sort(Comparator.comparing(ReviewDto::getReviewDate).reversed());

        return feedbackObjects;
    }

    public Double getAverageReviewForBike(String bikeId) {
        Double sum = 0.0;
        List<Review> review = reviewRepository.findByBikeId(bikeId);
        for (Review feedbackDto : review) {
            sum += feedbackDto.getNumberOfStars();
        }

        if (sum == 0.0) {
            return 0.0;
        }

        return sum / review.size();
    }

    public boolean deleteReview(String id) {
        if (!this.reviewRepository.existsById(id)) {
            return false;
        }

        this.reviewRepository.deleteById(id);
        return true;
    }

    public ReviewDto updateReview(String id, ReviewDto feedbackDto) throws NotFoundException {
        feedbackDto.setId(id);
        Review review = this.reviewRepository.findById(id).orElse(null);

        if (review == null) {
            throw new NotFoundException();
        }

        review.setNumberOfStars(feedbackDto.getNumberOfStars());
        review.setReview(feedbackDto.getReview());

        reviewRepository.save(review);

        return toReviewDto(review);
    }

    public void addReview(ReviewDto feedbackDto) {
        reviewRepository.save(toReview(feedbackDto));
    }

    private ReviewDto toReviewDto(Review review) {
        return new ReviewDto(review.getId(), review.getBikeId(), review.getTitle(), review.getReviewerId(),
                review.getNumberOfStars(), review.getReview(), review.getDate());
    }

    private Review toReview(ReviewDto feedbackDto) {
        return new Review(feedbackDto.getBikeId(), feedbackDto.getTitle(), feedbackDto.getReviewerId(),
                feedbackDto.getNumberOfStars(),
                feedbackDto.getReview());
    }

}
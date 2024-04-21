package soa.group11.reviewService.services;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import soa.group11.reviewService.entities.Review;
import soa.group11.reviewService.models.ReviewDto;
import soa.group11.reviewService.repositories.ReviewRepository;

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
        Review review = toReview(feedbackDto);
        review.setBikeName(getBikeName(feedbackDto.getBikeId()));

        reviewRepository.save(review);
    }

    private String getBikeName(String bikeId) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(
                    "http://bike-management.pedalpals:8082/bikes/" + bikeId + "/names", String.class);
            var bikeName = response.getBody();
            return bikeName;

        } catch (Exception e) {
            System.out.println("Bike not found...");
        }

        return "";

    }

    private ReviewDto toReviewDto(Review review) {
        return new ReviewDto(review.getId(), review.getBikeName(), review.getBikeId(), review.getReviewerName(),
                review.getTitle(), review.getReviewerId(),
                review.getNumberOfStars(), review.getReview(), review.getDate());
    }

    private Review toReview(ReviewDto feedbackDto) {
        return new Review(feedbackDto.getBikeId(), feedbackDto.getBikeName(), feedbackDto.getTitle(),
                feedbackDto.getReviewerName(), feedbackDto.getReviewerId(),
                feedbackDto.getNumberOfStars(),
                feedbackDto.getReview());
    }
}

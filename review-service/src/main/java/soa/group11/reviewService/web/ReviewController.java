package soa.group11.reviewService.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import soa.group11.reviewService.models.ReviewDto;
import soa.group11.reviewService.services.ReviewService;

@RestController
@Validated
public class ReviewController {
    @Autowired
    private ReviewService feedbackService;

    @ResponseBody
    @GetMapping("/reviews")
    public List<ReviewDto> getFeedbacks() {
        return feedbackService.getReviews();
    }

    @ResponseBody
    @PostMapping("/reviews")
    public void addFeedback(@Valid @RequestBody ReviewDto feedbackDto) {
        feedbackService.addReview(feedbackDto);
    }

    @ResponseBody
    @DeleteMapping("/reviews/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable String id) {
        if (feedbackService.deleteReview(id)) {
            return ResponseEntity.noContent().build();
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @PutMapping("/reviews/{id}")
    public ResponseEntity<ReviewDto> updateFeedback(@PathVariable String id,
            @RequestBody @Valid ReviewDto feedbackDto) {
        try {
            ReviewDto updatedFeedbackDto = feedbackService.updateReview(id, feedbackDto);
            return ResponseEntity.ok(updatedFeedbackDto);
        } catch (Exception e) {
            return new ResponseEntity<ReviewDto>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/reviews/average/{bikeId}")
    public Double getAverageReview(@PathVariable String bikeId) {
        return feedbackService.getAverageReviewForBike(bikeId);
    }
}

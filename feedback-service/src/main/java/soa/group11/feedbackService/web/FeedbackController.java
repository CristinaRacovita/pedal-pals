package soa.group11.feedbackService.web;

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
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import soa.group11.feedbackService.models.FeedbackDto;
import soa.group11.feedbackService.services.FeedbackService;

@RestController
@Validated
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/feedbacks")
    public List<FeedbackDto> getFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }


    @GetMapping("/feedbacks/{bikeId}")
    public ResponseEntity<List<FeedbackDto>> getFeedbacksByBikeId(@PathVariable String bikeId){
        List<FeedbackDto> feedbacks =  feedbackService.getFeedbacksByBikeId(bikeId);

        if (feedbacks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
          return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }


    @PostMapping("/feedback")
    public void addFeedback(@Valid @RequestBody FeedbackDto feedbackDto) {
        feedbackService.addFeedback(feedbackDto);
    }

    @DeleteMapping("/feedback/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable String id) {
        if (feedbackService.deleteFeedback(id) == true) {
            return ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/feedback/{id}")
    public ResponseEntity<FeedbackDto> updateFeedback(@PathVariable String id,
            @RequestBody @Valid FeedbackDto feedbackDto) {
        try {
            FeedbackDto updatedFeedbackDto = feedbackService.updateFeedback(id, feedbackDto);
            return ResponseEntity.ok(updatedFeedbackDto);
        } catch (Exception e) {
            System.out.println("On exception");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}

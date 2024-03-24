package soa.group11.feedbackService.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import soa.group11.feedbackService.models.FeedbackDto;
import soa.group11.feedbackService.services.FeedbackService;

@RestController
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/feedbacks")
    public List<FeedbackDto> getFeedbacks() {
        return feedbackService.getAllFeedbacks();
    }

    @PostMapping("/feedback")
    public void addFeedback(@RequestBody FeedbackDto feedbackDto){
        feedbackService.addFeedback(feedbackDto);
    }

    @DeleteMapping("/feedback/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable String id){
        feedbackService.deleteFeedback(id);
        return ResponseEntity.noContent().build();
    }

}

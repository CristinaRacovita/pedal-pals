package soa.group11.feedbackService.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}

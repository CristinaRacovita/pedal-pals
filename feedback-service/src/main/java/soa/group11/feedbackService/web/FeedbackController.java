package soa.group11.feedbackService.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;

import jakarta.validation.Valid;
import soa.group11.feedbackService.models.FeedbackDto;
import soa.group11.feedbackService.services.FeedbackService;

@Controller
@Validated
public class FeedbackController {
    @Autowired
    private FeedbackService feedbackService;

    @ResponseBody
    @GetMapping("/feedbacks")
    public List<FeedbackDto> getFeedbacks() {
        return feedbackService.getAllFeedback();
    }

    @GetMapping("/feedbacks/{bikeId}")
    public String getFeedbacksByBikeIds(Model model, @PathVariable List<String> bikeId) {
        List<FeedbackDto> feedbacks = feedbackService.getFeedbackByBikeIds(bikeId);

        if (feedbacks.isEmpty()) {
            return "404 Not Found";
        }

        model.addAttribute("bikeId", bikeId.getFirst());
        model.addAttribute("feedbacks", feedbacks);
        return "reviews_overview";
    }

    @ResponseBody
    @PostMapping("/feedback")
    public void addFeedback(@Valid @RequestBody FeedbackDto feedbackDto) {
        feedbackService.addFeedback(feedbackDto);
    }

    @ResponseBody
    @DeleteMapping("/feedback/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable String id) {
        if (feedbackService.deleteFeedback(id) == true) {
            return ResponseEntity.noContent().build();
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @PutMapping("/feedback/{id}")
    public ResponseEntity<FeedbackDto> updateFeedback(@PathVariable String id,
            @RequestBody @Valid FeedbackDto feedbackDto) {
        try {
            FeedbackDto updatedFeedbackDto = feedbackService.updateFeedback(id, feedbackDto);
            return ResponseEntity.ok(updatedFeedbackDto);
        } catch (Exception e) {
            return new ResponseEntity<FeedbackDto>(HttpStatus.NOT_FOUND);
        }
    }
}

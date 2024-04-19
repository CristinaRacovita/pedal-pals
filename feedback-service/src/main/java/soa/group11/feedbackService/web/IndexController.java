package soa.group11.feedbackService.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import soa.group11.feedbackService.models.FeedbackDto;
import soa.group11.feedbackService.services.FeedbackService;

@Controller
public class IndexController {
    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("{userId}/rating/{bikeId}")
    public String getFeedbacksByBikeId(Model model, @PathVariable String bikeId, @PathVariable int userId) {
        List<FeedbackDto> feedbacks = feedbackService.getFeedbackByBikeId(bikeId);

        if (feedbacks.isEmpty()) {
            return "reviews_overview";
        }

        model.addAttribute("bikeId", bikeId);
        model.addAttribute("feedbacks", feedbacks);
        model.addAttribute("userId", userId);

        return "reviews_overview";
    }

    @GetMapping("/rating/{userId}/{bikeId}")
    public String getReviewForm(Model model, @PathVariable int userId, @PathVariable String bikeId) {
        model.addAttribute("userId", userId);
        model.addAttribute("bikeId", bikeId);

        return "leave_review";
    }
}

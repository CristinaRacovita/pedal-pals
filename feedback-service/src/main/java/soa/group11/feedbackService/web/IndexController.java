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
}

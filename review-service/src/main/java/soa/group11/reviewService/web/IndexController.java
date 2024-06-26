package soa.group11.reviewService.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import soa.group11.reviewService.models.ReviewDto;
import soa.group11.reviewService.services.ReviewService;

@Controller
public class IndexController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/review/{bikeId}")
    public String getFeedbacksByBikeId(Model model, @PathVariable String bikeId) {
        List<ReviewDto> reviews = reviewService.getReviewsByBikeId(bikeId);

        if (reviews.isEmpty()) {
            return "reviews_overview";
        }

        model.addAttribute("bikeName", reviews.get(0).getBikeName());
        model.addAttribute("reviews", reviews);

        return "reviews_overview";
    }

    @GetMapping("/new-review/{bikeId}")
    public String getReviewForm(Model model, @PathVariable String bikeId) {
        model.addAttribute("bikeId", bikeId);

        return "leave_review";
    }
}

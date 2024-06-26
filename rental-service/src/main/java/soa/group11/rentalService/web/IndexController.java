package soa.group11.rentalService.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import soa.group11.rentalService.services.RentalsService;

@Controller
public class IndexController {
    @Autowired
    private RentalsService rentalsService;

    @GetMapping(value = "/rentals/{userId}")
    public String getBikes(@PathVariable(value = "userId") int userId, Model model) {
        model.addAttribute("bikes_rented_in", rentalsService.getBikeRentals(userId, false));
        model.addAttribute("bikes_rented_out", rentalsService.getBikeRentals(userId, true));

        return "user_rentals";
    }
}

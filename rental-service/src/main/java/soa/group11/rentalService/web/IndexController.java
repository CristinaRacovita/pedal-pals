package soa.group11.rentalService.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import soa.group11.rentalService.models.BikeDto;
import soa.group11.rentalService.services.RentalsService;

@RestController
public class IndexController {
    @Autowired
    private RentalsService rentalsService;

    @GetMapping(value = "/{userId}/my-bikes")
    public List<BikeDto> getBikes(@PathVariable(value = "userId") String userId) {
        // model.addAttribute("rented_in_bikes",
        // rentalApprovalService.getBikesRentedIn(userId));
        // model.addAttribute("rented_out_bikes",
        // rentalRequestService.getBikesRentedOut(userId));

        // return "user_rentals";
        return rentalsService.getBikeRentals(userId, false);
    }
}

package soa.group11.rentalService.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import soa.group11.rentalService.models.BikeDto;
import soa.group11.rentalService.services.RentalApprovalService;
import soa.group11.rentalService.services.RentalRequestService;

@RestController
public class IndexController {
    @Autowired
    private RentalApprovalService rentalApprovalService;

    @Autowired
    private RentalRequestService rentalRequestService;

    @GetMapping(value = "/{userId}/my-bikes")
    public List<BikeDto> getBikes(@PathVariable(value = "userId") String userId) {
        //model.addAttribute("rented_in_bikes", rentalApprovalService.getBikesRentedIn(userId));
        //model.addAttribute("rented_out_bikes", rentalRequestService.getBikesRentedOut(userId));

        //return "user_rentals";
        return rentalApprovalService.getBikesRentedIn(userId);
    }
}

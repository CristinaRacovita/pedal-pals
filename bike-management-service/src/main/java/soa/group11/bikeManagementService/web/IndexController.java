package soa.group11.bikeManagementService.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import soa.group11.bikeManagementService.models.BikeCardDto;
import soa.group11.bikeManagementService.models.BikeDetailsDto;
import soa.group11.bikeManagementService.services.BikeService;
import soa.group11.bikeManagementService.services.UserService;

@Controller
public class IndexController {
    @Autowired
    private BikeService bikeService;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/{userId}/bikes")
    public String getBikes(@PathVariable(value = "userId") int userId, Model model) {
        List<BikeCardDto> bikes = bikeService.getAllBikes();
        model.addAttribute("bikes", bikes);
        model.addAttribute("userId", userId);
        return "bikes_overview";
    }

    @GetMapping("/{userId}/filter")
    public String filter(@PathVariable(value = "userId") int userId,
            @RequestParam(name = "wheelSize", required = false) String wheelSize,
            @RequestParam(name = "numberOfGears", required = false) String numberOfGears,
            @RequestParam(name = "startRentingDate", required = false) String startRentingDate,
            @RequestParam(name = "endRentingDate", required = false) String endRentingDate,
            @RequestParam(name = "brand", required = false) String brand,
            @RequestParam(name = "type", required = false) String type,
            @RequestParam(name = "suitability", required = false) String suitability,
            Model model) {

        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

            int wheelSizeInt = wheelSize == "" ? -1 : Integer.parseInt(wheelSize);
            int numberOfGearsInt = numberOfGears == "" ? -1 : Integer.parseInt(numberOfGears);
            Date convertedStartRentingDate = startRentingDate == "" ? null : formatter.parse(startRentingDate);
            Date convertedEndRentingDate = endRentingDate == "" ? null : formatter.parse(endRentingDate);

            List<BikeCardDto> bikes = this.bikeService.filterBikes(wheelSizeInt,
                    numberOfGearsInt,
                    convertedStartRentingDate,
                    convertedEndRentingDate,
                    brand == "" ? null : brand,
                    type == "" ? null : type,
                    suitability == "" ? null : suitability);
            model.addAttribute("bikes", bikes);
            model.addAttribute("userId", userId);
        } catch (ParseException ex) {

        }
        return "bikes_overview";
    }

    @GetMapping(value = "/{userId}/bike/{bikeId}/{isOverview}")
    public String getBikeDetails(@PathVariable(value = "userId") int userId,
            @PathVariable(value = "bikeId") String bikeId, @PathVariable(value = "isOverview") int isOverview,
            Model model) {
        BikeDetailsDto bike = bikeService.getBikeDetails(bikeId);
        var averageScore = bikeService.getAverageScoreForBike(bikeId);

        model.addAttribute("bike", bike);
        model.addAttribute("userId", userId);
        model.addAttribute("isOverview", isOverview);
        model.addAttribute("averageScore", averageScore);

        return "selected_bike";
    }

    @GetMapping(value = "/my-bikes/{userId}")
    public String getBikesByUserId(@PathVariable(value = "userId") int userId, Model model) {
        List<BikeCardDto> bikes = bikeService.getBikesByUserId(userId);
        String username = userService.getUsernameById(userId);

        model.addAttribute("bikes", bikes);
        model.addAttribute("userId", userId);
        model.addAttribute("username", username);

        return "my_bikes";
    }

    @GetMapping(value = "/bikes/{userId}")
    public String getAddBikePage(@PathVariable(value = "userId") int userId, Model model) {
        return "add_bike";
    }
}

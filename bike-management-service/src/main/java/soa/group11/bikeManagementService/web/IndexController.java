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
import org.springframework.web.bind.annotation.RequestParam;

import soa.group11.bikeManagementService.models.BikeCardDto;
import soa.group11.bikeManagementService.services.BikeService;

@Controller
public class IndexController {
    @Autowired
    private BikeService bikeService;

    @GetMapping(value = "/bikes")
    public String getBikes(Model model) {
        List<BikeCardDto> bikes = bikeService.getAllBikes();
        model.addAttribute("bikes", bikes);
        return "bikes_overview";
    }

    @GetMapping("/filter")
    public String filter(@RequestParam(name = "wheelSize", required = false) String wheelSize,
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
        } catch (ParseException ex) {
            
        }
        return "bikes_overview";
    }
}

package soa.group11.bikeManagementService.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}

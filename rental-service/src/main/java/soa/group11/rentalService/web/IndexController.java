package soa.group11.rentalService.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {
    @GetMapping(value = "/{userId}/my-bikes")
    public String getBikes(@PathVariable(value = "userId") int userId, Model model) {
        return "user_bikes";
    } 
}

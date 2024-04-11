package soa.group11.authenticationService.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import soa.group11.authenticationService.models.UserDto;

import org.springframework.stereotype.Controller;

@Controller
public class IndexController {

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView model = new ModelAndView("login");
        model.addObject("user", new UserDto());
        return model;
    }
}

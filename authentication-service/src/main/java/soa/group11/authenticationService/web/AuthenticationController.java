package soa.group11.authenticationService.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import soa.group11.authenticationService.models.User;

import org.springframework.stereotype.Controller;

@Controller
public class AuthenticationController {

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView model = new ModelAndView("login");
        model.addObject("user", new User());
        return model;
    }
}

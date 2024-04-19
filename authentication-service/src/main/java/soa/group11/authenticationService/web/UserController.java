package soa.group11.authenticationService.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import soa.group11.authenticationService.models.UserDto;
import soa.group11.authenticationService.services.UserService;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users/username/{userId}")
    public String getUsernameById(@PathVariable("userId") int userId) {
        return userService.getUsernameById(userId);
    }

    @PostMapping("/authentication")
    public int login(@RequestBody UserDto userDto) {
        return userService.checkCredentials(userDto);
    }
}

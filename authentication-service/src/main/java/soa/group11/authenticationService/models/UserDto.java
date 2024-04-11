package soa.group11.authenticationService.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String username;
    private String password;

    public UserDto() {
    }

    public UserDto(String username, String password) {
        this.password = password;
        this.username = username;
    }
}

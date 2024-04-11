package soa.group11.authenticationService.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

import soa.group11.authenticationService.models.UserDto;

@Service
public class UserService {
    Map<Integer, UserDto> myUsers = new HashMap<Integer, UserDto>() {
        {
            put(1, new UserDto("cristinaracovita", "1234"));
            put(2, new UserDto("bogdanbindila", "1234"));
        }
    };

    public String getUsernameById(int id) {
        return myUsers.get(id).getUsername();
    }

    public int checkCredentials(UserDto userDto) {
        for (var credential : myUsers.entrySet()) {
            var user = credential.getValue();
            if (user.getPassword().equals(userDto.getPassword()) && user.getUsername().equals(userDto.getUsername())) {
                return credential.getKey();
            }
        }

        return -1;
    }
}

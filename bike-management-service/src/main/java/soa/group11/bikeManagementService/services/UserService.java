package soa.group11.bikeManagementService.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    public String getUsernameById(int userId) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(
                    "http://authentication.pedalpals:8080/users/username/" + userId, String.class);

            String username = response.getBody();

            if (username == null) {
                return "User";
            }

            return username;

        } catch (Exception e) {
            System.out.println("Reviews not found! --- " + e.getMessage());
            return "User";
        }
    }

    public String getPhoneById(int userId) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.getForEntity(
                    "http://authentication.pedalpals:8080/users/phone/" + userId, String.class);

            String username = response.getBody();

            if (username == null) {
                return "User";
            }

            return username;

        } catch (Exception e) {
            System.out.println("Reviews not found! --- " + e.getMessage());
            return "User";
        }
    }


}

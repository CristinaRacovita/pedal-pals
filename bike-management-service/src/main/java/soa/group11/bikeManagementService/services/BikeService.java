package soa.group11.bikeManagementService.services;

import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import soa.group11.bikeManagementService.entities.Bike;
import soa.group11.bikeManagementService.entities.BikeType;
import soa.group11.bikeManagementService.models.BikeDto;
import soa.group11.bikeManagementService.models.FeedbackDto;
import soa.group11.bikeManagementService.repositories.BikeRepository;

@Service
public class BikeService {
    @Autowired
    private BikeRepository bikeRepository;

    public List<BikeDto> getBikesByUserId(int userId) {
        List<BikeDto> bikeDtos = bikeRepository.findByUserId(userId).stream().map(bike -> ToBikeDto(bike))
                .collect(Collectors.toList());

        addFeedbacks(bikeDtos);
        return bikeDtos;
    }

    public void addBike(BikeDto bikeDto) {
        bikeRepository.save(ToBike(bikeDto));
    }

    private void addFeedbacks(List<BikeDto> bikeDtos) {
        // get feedbacks
        String bikeIds = "";

        for (BikeDto bikeDto : bikeDtos) {
            bikeIds += bikeDto.getId();
            bikeIds += ",";
        }

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<FeedbackDto[]> response = restTemplate.getForEntity(
                    "http://localhost:8081/feedbacks/" + bikeIds,
                    FeedbackDto[].class);

            FeedbackDto[] feedbackDtos = response.getBody();

            // set feedbacks
            for (FeedbackDto feedbackDto : feedbackDtos) {
                for (BikeDto bikeDto : bikeDtos)
                    if (bikeDto.getId().equals(feedbackDto.getBikeId())) {
                        bikeDto.getFeedbacks().add(feedbackDto);
                    }
            }

        } catch (Exception e) {
            System.out.println("Feedbacks not found!");
        }
    }

    private BikeDto ToBikeDto(Bike bike) {
        return new BikeDto(bike.getId(), bike.getUserId(), bike.getBrand(),
                bike.getBikeType().getSuitability().equals("CITY"));
    }

    private Bike ToBike(BikeDto bike) {
        return new Bike(bike.getUserId(), bike.getBrand(),
                new BikeType(bike.getIsTownBike() ? "CITY" : "MOUNTAIN"));
    }
}

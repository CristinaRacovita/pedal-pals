package soa.group11.bikeManagementService.services;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.text.Format;
import java.text.SimpleDateFormat;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import soa.group11.bikeManagementService.entities.Bike;
import soa.group11.bikeManagementService.models.BikeCardDto;
import soa.group11.bikeManagementService.models.BikeDetailsDto;
import soa.group11.bikeManagementService.models.BikeDto;
import soa.group11.bikeManagementService.models.FeedbackDto;
import soa.group11.bikeManagementService.repositories.BikeRepository;
import soa.group11.bikeManagementService.repositories.CustomBikeRepository;

@Service
public class BikeService {
    @Autowired
    private BikeRepository bikeRepository;
    @Autowired
    private CustomBikeRepository customBikeRepository;

    public List<BikeDto> getBikesByUserId(int userId) {
        List<BikeDto> bikeDtos = bikeRepository.findByUserId(userId).stream().map(bike -> toBikeDto(bike))
                .collect(Collectors.toList());

        return getBikesWithFeedback(bikeDtos);
    }

    public BikeDetailsDto getBikeDetails(String bikeId) {
        Optional<Bike> bike = this.bikeRepository.findById(bikeId);
        if (!bike.isPresent()) {
            return null;
        }

        return toBikeDetails(bike.get());
    }

    public void addBike(BikeDto bikeDto) {
        Bike bike = toBike(bikeDto);
        if (bike == null) {
            return;
        }

        bikeRepository.save(bike);
    }

    public List<BikeCardDto> getAllBikes() {
        return bikeRepository.findAll().stream().map(bike -> toBikeCardDto(bike))
                .collect(Collectors.toList());
    }

    public List<BikeCardDto> filterBikes(int wheelSize,
            int numberOfGears,
            Date startRentingDate,
            Date endRentingDate,
            String brand,
            String type,
            String suitability) {

        return customBikeRepository.findByFilterCriteria(wheelSize,
                numberOfGears,
                startRentingDate,
                endRentingDate,
                brand,
                type,
                suitability).stream().map(bike -> toBikeCardDto(bike))
                .collect(Collectors.toList());

    }

    private List<BikeDto> getBikesWithFeedback(List<BikeDto> bikeDtos) {
        StringBuilder bikeIdsBuilder = new StringBuilder();

        for (BikeDto bikeDto : bikeDtos) {
            bikeIdsBuilder.append(bikeDto.getId()).append(",");
        }

        String bikeIds = bikeIdsBuilder.toString();
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<FeedbackDto[]> response = restTemplate.getForEntity(
                    "http://localhost:8081/feedbacks/" + bikeIds, FeedbackDto[].class);

            FeedbackDto[] feedbackDtos = response.getBody();

            if (feedbackDtos == null) {
                return bikeDtos;
            }

            Map<String, List<FeedbackDto>> bikeIdToFeedbacks = Arrays.stream(feedbackDtos)
                    .collect(Collectors.groupingBy(FeedbackDto::getBikeId));

            for (BikeDto bikeDto : bikeDtos) {
                List<FeedbackDto> bikeFeedbacks = bikeIdToFeedbacks.get(bikeDto.getId());
                if (bikeFeedbacks != null) {
                    bikeDto.getFeedbacks().addAll(bikeFeedbacks);
                }
            }

        } catch (Exception e) {
            System.out.println("Feedbacks not found! --- " + e.getMessage());
        }

        return bikeDtos;
    }

    private BikeCardDto toBikeCardDto(Bike bike) {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        return new BikeCardDto(
                bike.getId(),
                bike.getName(),
                formatter.format(bike.getStartRentingDate()),
                formatter.format(bike.getEndRentingDate()),
                bike.getBikeImage() == null ? null : Base64.encodeBase64String(bike.getBikeImage().getData()));
    }

    private BikeDetailsDto toBikeDetails(Bike bike) {
        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        return new BikeDetailsDto(bike.getId(), bike.getWheelSize(), bike.getNumberOfGears(), bike.getName(),
                bike.getBrand(), bike.getColor(), bike.getUserId(), formatter.format(bike.getStartRentingDate()),
                formatter.format(bike.getEndRentingDate()),
                bike.getBikeImage() == null ? null : Base64.encodeBase64String(bike.getBikeImage().getData()),
                bike.getType(), bike.getSuitability());
    }

    private BikeDto toBikeDto(Bike bike) {
        return new BikeDto(bike.getId(), bike.getUserId(), bike.getBrand(),
                bike.getSuitability().equals("CITY"));
    }

    private Bike toBike(BikeDto bike) {
        return new Bike(bike.getUserId(), bike.getBrand(),
                null, bike.getIsTownBike() ? "CITY" : "MOUNTAIN");
    }
}

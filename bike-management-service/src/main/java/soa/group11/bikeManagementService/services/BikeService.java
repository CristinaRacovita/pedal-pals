package soa.group11.bikeManagementService.services;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.text.Format;
import java.text.ParseException;
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
import soa.group11.bikeManagementService.models.NewBikeDto;
import soa.group11.bikeManagementService.repositories.BikeRepository;
import soa.group11.bikeManagementService.repositories.CustomBikeRepository;

@Service
public class BikeService {
    @Autowired
    private BikeRepository bikeRepository;
    @Autowired
    private CustomBikeRepository customBikeRepository;

    public List<BikeCardDto> getBikesByUserId(int userId) {
        List<BikeCardDto> bikeDtos = bikeRepository.findByUserId(userId).stream().map(bike -> toBikeCardDto(bike))
                .collect(Collectors.toList());

        return bikeDtos;
    }

    public BikeDetailsDto getBikeDetails(String bikeId) {
        Optional<Bike> bike = this.bikeRepository.findById(bikeId);
        if (!bike.isPresent()) {
            return null;
        }

        return toBikeDetails(bike.get());
    }

    public String addBike(NewBikeDto bikeDto) {
        Bike bike = toBike(bikeDto);
        if (bike == null) {
            return "";
        }

        return bikeRepository.save(bike).getId();
    }

    public void updateBike(BikeDetailsDto bike) {
        Optional<Bike> currentBike = bikeRepository.findById(bike.getId());
        if (!currentBike.isPresent()) {
            return;
        }

        Bike bikeToUpdate = currentBike.get();

        bikeToUpdate.setWheelSize(bike.getWheelSize());
        bikeToUpdate.setNumberOfGears(bike.getNumberOfGears());
        bikeToUpdate.setBrand(bike.getBrand());
        bikeToUpdate.setEndRentingDate(toDate(bike.getEndRentingDate()));
        bikeToUpdate.setStartRentingDate(toDate(bike.getStartRentingDate()));
        bikeToUpdate.setType(bike.getType());
        bikeToUpdate.setSuitability(bike.getSuitability());

        bikeRepository.save(bikeToUpdate);
    }

    public List<BikeCardDto> getAllBikes() {
        return bikeRepository.findAll().stream().map(bike -> toBikeCardDto(bike))
                .collect(Collectors.toList());
    }

    public void deleteBike(String bikeId) {
        bikeRepository.deleteById(bikeId);
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

    private Date toDate(String stringDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatter.parse(stringDate);
        } catch (ParseException e) {
            return null;
        }

    }

    public Double getAverageScoreForBike(String bikeId) {

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Double> response = restTemplate.getForEntity(
                    "http://localhost:8081/feedback/" + bikeId, Double.class);

            Double averageScore = response.getBody();

            if (averageScore == null) {
                return 0.0;
            }

            return averageScore;

        } catch (Exception e) {
            System.out.println("Feedbacks not found! --- " + e.getMessage());
            return 0.0;
        }
    }

    public BikeCardDto getBikeById(String bikeId) {
        return toBikeCardDto(bikeRepository.findById(bikeId).orElse(null));
    }

    private BikeCardDto toBikeCardDto(Bike bike) {
        if (bike == null) {
            return null;
        }

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

    private Bike toBike(NewBikeDto bike) {
        return new Bike(bike.getWheelSize(), bike.getNumberOfGears(), bike.getName(), bike.getBrand(), bike.getColor(),
                bike.getUserId(),
                toDate(bike.getStartRentingDate()), toDate(bike.getEndRentingDate()), bike.getType(),
                bike.getSuitability());
    }

}

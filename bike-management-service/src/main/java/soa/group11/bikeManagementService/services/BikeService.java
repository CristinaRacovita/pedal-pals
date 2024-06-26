package soa.group11.bikeManagementService.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        bikeToUpdate.setColor(bike.getColor());
        bikeToUpdate.setNumberOfGears(bike.getNumberOfGears());
        bikeToUpdate.setBrand(bike.getBrand());
        bikeToUpdate.setEndRentingDate(toDate(bike.getEndRentingDate()));
        bikeToUpdate.setStartRentingDate(toDate(bike.getStartRentingDate()));
        bikeToUpdate.setType(bike.getType());
        bikeToUpdate.setSuitability(bike.getSuitability());

        bikeRepository.save(bikeToUpdate);
    }

    public List<BikeCardDto> getAllBikes() {
        List<BikeCardDto> allBikes = bikeRepository.findAll().stream().map(bike -> toBikeCardDto(bike))
                .collect(Collectors.toList());

        List<BikeCardDto> bikesToRemove = new ArrayList<>();

        for (BikeCardDto bikeCardDto : allBikes) {
            boolean availableForRent = getRentalAvailability(bikeCardDto.getId(), bikeCardDto.getStartRentingDate(),
                    bikeCardDto.getEndRentingDate());

            if (!availableForRent) {
                bikesToRemove.add(bikeCardDto);
            }
        }

        allBikes.removeAll(bikesToRemove);

        return allBikes;
    }

    public void deleteBike(String bikeId) {
        bikeRepository.deleteById(bikeId);
    }

    public List<BikeCardDto> filterBikes(int wheelSize,
            String color,
            int numberOfGears,
            Date startRentingDate,
            Date endRentingDate,
            String brand,
            String type,
            String suitability) {

        return customBikeRepository.findByFilterCriteria(wheelSize,
                color,
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
                    "http://review.pedalpals:8081/reviews/average/" + bikeId, Double.class);

            Double averageScore = response.getBody();

            if (averageScore == null) {
                return 0.0;
            }

            return round(averageScore, 2);

        } catch (Exception e) {
            System.out.println("Reviews not found! --- " + e.getMessage());
            return 0.0;
        }
    }

    public BikeCardDto getBikeById(String bikeId) {
        return toBikeCardDto(bikeRepository.findById(bikeId).orElse(null));
    }

    public double round(double value, int places) {
        if (places < 0)
            throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
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

    public boolean getRentalAvailability(String bikeId, String startRentingDate, String endRentingDate) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Boolean> response = restTemplate.getForEntity(
                    "http://rental.pedalpals:8083/approvals/available/" + bikeId + "?startDate=" + startRentingDate
                            + "&endDate=" + endRentingDate,
                    boolean.class);
                    
            var availableForRent = response.getBody();
            if (availableForRent == null) {
                return true;
            }

            return availableForRent;

        } catch (Exception e) {
            System.out.println("Bike not found...");
            return false;
        }
    }

    public String getBikeName(String bikeId) {
        Bike bike = bikeRepository.findById(bikeId).orElse(null);

        if (bike == null) {
            return "";
        } else {
            return bike.getName();
        }

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

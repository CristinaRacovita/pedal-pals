package soa.group11.bikeManagementService.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soa.group11.bikeManagementService.entities.Bike;
import soa.group11.bikeManagementService.entities.BikeType;
import soa.group11.bikeManagementService.models.BikeDto;
import soa.group11.bikeManagementService.repositories.BikeRepository;

@Service
public class BikeService {
    @Autowired
    private BikeRepository bikeRepository;

    public List<BikeDto> getBikesByUserId(int userId) {
        return bikeRepository.findByUserId(userId).stream().map(bike -> ToBikeDto(bike)).collect(Collectors.toList());
    }

    public void addBike(BikeDto bikeDto) {
        bikeRepository.save(ToBike(bikeDto));
    }

    private BikeDto ToBikeDto(Bike bike) {
        return new BikeDto(bike.getBrand(), bike.getNumberOfGears(), bike.getBikeType().getSuitability().equals("CITY"));
    }

    private Bike ToBike(BikeDto bike) {
        return new Bike(bike.getUserId(), bike.getBrand(),
                new BikeType(bike.getIsTownBike() ? "CITY" : "MOUNTAIN"));
    }
}

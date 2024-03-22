package soa.group11.bikeManagementService.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soa.group11.bikeManagementService.entities.Bike;
import soa.group11.bikeManagementService.enums.SuitableRoad;
import soa.group11.bikeManagementService.models.BikeDto;
import soa.group11.bikeManagementService.repositories.BikeRepository;

@Service
public class BikeService {

    private final BikeRepository bikeRepository;

    @Autowired
    public BikeService(BikeRepository bikeRepository) {
        this.bikeRepository = bikeRepository;
    }

    public List<BikeDto> getBikesByUserId(int userId) {
        return bikeRepository.getBikesByUserId(userId).stream().map(bike->ToBikeDto(bike)).collect(Collectors.toList());
    }

    private BikeDto ToBikeDto(Bike bike){
        return new BikeDto(bike.getBrand(), bike.getBikeType().getSuitability().equals(SuitableRoad.TOWN));
    }
}

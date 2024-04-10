package soa.group11.rentalService.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import soa.group11.rentalService.entities.RentalApproval;
import soa.group11.rentalService.entities.RentalRequest;
import soa.group11.rentalService.models.BikeDto;
import soa.group11.rentalService.models.RentalApprovalDto;
import soa.group11.rentalService.repositories.RentalApprovalRepository;
import soa.group11.rentalService.repositories.RentalRequestRepository;

@Service
public class RentalsService {
    @Autowired
    private RentalRequestRepository rentalRequestRepository;

    @Autowired
    private RentalApprovalRepository rentalApprovalRepository;

    public List<BikeDto> getBikeRentals(String userId, boolean rentedOut) {
        List<BikeDto> bikeRentals = new ArrayList<>();
        List<RentalRequest> rentalRequests = new ArrayList<>();

        List<RentalApproval> rentalApprovals = rentalApprovalRepository.findAll();

        if (rentedOut == true) {
            rentalRequests = rentalRequestRepository.findAllByBikeOwnerId(userId);
        } else {
            rentalRequests = rentalRequestRepository.findAllByBikeRequesterId(userId);
        }

        for (RentalRequest rentalRequest : rentalRequests) {
            BikeDto bike = getBikeByBikeId(rentalRequest.getBikeId());

            if (bike != null) {
                bike.setStartRentingDate(rentalRequest.getStringStartDate());
                bike.setEndRentingDate(rentalRequest.getStringEndDate());
                
                if (rentedOut == true) {
                    bike.setPersonOfContact(rentalRequest.getBikeRequesterId());
                } else {
                    bike.setPersonOfContact(rentalRequest.getBikeOwnerId());
                }
                bike.setRequestStatus(rentalRequest.getStatus());

                for (RentalApproval approval : rentalApprovals) {
                    if (approval.getRequestId().equals(rentalRequest.getId())) {
                        bike.setApprovalStatus(approval.getApprovalStatus());
                    }
                }
                bikeRentals.add(bike);
            }
        }
        return bikeRentals;
    }

    private BikeDto getBikeByBikeId(String bikeId) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<BikeDto> response = restTemplate.getForEntity(
                    "http://localhost:8082/bike/" + bikeId, BikeDto.class);

            BikeDto bikeDto = response.getBody();
            return bikeDto;

        } catch (Exception e) {
            System.out.println("Bike not found...");
            return null;
        }
    }
}

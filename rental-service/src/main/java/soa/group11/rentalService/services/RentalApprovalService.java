package soa.group11.rentalService.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import soa.group11.rentalService.entities.RentalApproval;
import soa.group11.rentalService.entities.RentalRequest;
import soa.group11.rentalService.models.BikeDto;
import soa.group11.rentalService.models.RentalApprovalDto;
import soa.group11.rentalService.repositories.RentalApprovalRepository;
import soa.group11.rentalService.repositories.RentalRequestRepository;

@Service
public class RentalApprovalService {
    @Autowired
    private RentalApprovalRepository rentalApprovalRepository;

    @Autowired
    private RentalRequestRepository rentalRequestRepository;

    public List<BikeDto> getBikesRentedIn(String userId) {
        List<BikeDto> bikesRentedIn = new ArrayList<>();

        List<RentalApprovalDto> approvals = rentalApprovalRepository.findAll().stream()
                .map(rentalApproval -> toRentalApprovalDto(rentalApproval))
                .collect(Collectors.toList());

        List<RentalRequest> requestsForOwner = rentalRequestRepository.findAllByBikeOwnerId(userId);

        for (RentalRequest rentalRequest : requestsForOwner) {
            BikeDto bike = getBikeByBikeId(rentalRequest.getBikeId());

            if (bike != null){
                bike.setPersonOfContact(rentalRequest.getBikeRequesterId());
                bike.setRequestStatus(rentalRequest.getStatus());
    
                for (RentalApprovalDto approval : approvals) {
                    if (approval.getRequestId().equals(rentalRequest.getId())) {
                        bike.setApprovalStatus(approval.getApprovalStatus());
                    }
                }
                bikesRentedIn.add(bike);
            }
        }

        return bikesRentedIn;
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

    public List<RentalApprovalDto> getApprovalStatuses() {
        return rentalApprovalRepository.findAll().stream().map(rentalApproval -> toRentalApprovalDto(rentalApproval))
                .collect(Collectors.toList());
    }

    public RentalRequest addApprovalStatus(RentalApprovalDto rentalApprovalDto) {
        if (!rentalApprovalDto.getApprovalStatus().equals("approved")
                && !rentalApprovalDto.getApprovalStatus().equals("declined")) {
            return null;
        }

        RentalRequest rentalRequest = rentalRequestRepository.findById(rentalApprovalDto.getRequestId()).orElse(null);

        if (rentalRequest == null) {
            return null;
        }

        if (!rentalRequest.getStatus().equals("cancelled")) {
            rentalApprovalRepository.save(toRentalApproval(rentalApprovalDto));
            return rentalRequest;
        }

        return null;
    }

    public RentalApprovalDto toRentalApprovalDto(RentalApproval rentalApproval) {
        return new RentalApprovalDto(rentalApproval.getId(), rentalApproval.getRequestId(),
                rentalApproval.getApprovalStatus(),
                rentalApproval.getDetails());
    }

    public RentalApproval toRentalApproval(RentalApprovalDto rentalApprovalDto) {
        return new RentalApproval(rentalApprovalDto.getRequestId(), rentalApprovalDto.getApprovalStatus(),
                rentalApprovalDto.getDetails());
    }
}

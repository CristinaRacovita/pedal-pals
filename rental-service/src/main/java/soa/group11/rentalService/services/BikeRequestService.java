package soa.group11.rentalService.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soa.group11.rentalService.entities.RentalRequest;
import soa.group11.rentalService.models.RentalRequestDto;
import soa.group11.rentalService.repositories.RentalRequestRepository;

@Service
public class BikeRequestService {
    @Autowired
    private RentalRequestRepository rentalRequestRepository;


    public List<RentalRequestDto> getRequests(String bikeId, String requesterId) {
        List<RentalRequest> rentalRequests;

        if (bikeId != null && requesterId != null){
            rentalRequests = rentalRequestRepository.findAllByBikeIdAndBikeRequesterId(bikeId, requesterId);
        } else if (bikeId == null && requesterId == null) {
            rentalRequests = rentalRequestRepository.findAll();
        } else {
            if (bikeId == null) {
                rentalRequests = rentalRequestRepository.findAllByRequesterId(requesterId);
            } else {
                rentalRequests = rentalRequestRepository.findAllByBikeId(bikeId);
            }
        }

        return rentalRequests.stream().map(rentalRequest -> toRentalRequestDto(rentalRequest))
                .collect(Collectors.toList());
    }

    public void addRentalRequest(RentalRequestDto rentalRequestDto) {
        rentalRequestDto.setStatus("sent");
        rentalRequestRepository.save(toRentalRequest(rentalRequestDto));
    }

    public RentalRequestDto cancelRequest(String id, RentalRequestDto rentalRequestDto) throws Exception {
        // make sure the object matches the path variable
        rentalRequestDto.setId(id);
        RentalRequest rentalRequest = this.rentalRequestRepository.findById(id).orElse(null);

        if (rentalRequest != null) {
            // only the status can be updated

            if (!rentalRequest.getStatus().equals("cancelled")){
                rentalRequest.setStatus(rentalRequestDto.getStatus());

                rentalRequestRepository.save(rentalRequest);
    
                return toRentalRequestDto(rentalRequest);
            }else{
                return null;
            }
        } else {
            throw new Exception("Bike request with ID " + id + " not found");
        }
    }

    public RentalRequestDto toRentalRequestDto(RentalRequest rentalRequest) {
        return new RentalRequestDto(rentalRequest.getId(), rentalRequest.getBikeOwnerId(), rentalRequest.getBikeRequesterId(),
                rentalRequest.getBikeId(), rentalRequest.getStatus());
    }

    public RentalRequest toRentalRequest(RentalRequestDto rentalRequestDto) {
        return new RentalRequest(rentalRequestDto.getBikeOwnerId(), rentalRequestDto.getBikeRequesterId(),
                rentalRequestDto.getBikeId(), rentalRequestDto.getStatus());
    }
}

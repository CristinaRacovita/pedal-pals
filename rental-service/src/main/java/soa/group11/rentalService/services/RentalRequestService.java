package soa.group11.rentalService.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import soa.group11.rentalService.entities.RentalApproval;
import soa.group11.rentalService.entities.RentalRequest;
import soa.group11.rentalService.models.RentalApprovalDto;
import soa.group11.rentalService.models.RentalRequestDto;
import soa.group11.rentalService.repositories.RentalRequestRepository;

@Service
public class RentalRequestService {
    @Autowired
    private RentalRequestRepository rentalRequestRepository;

    public List<RentalRequestDto> getRequests(String bikeId, String requesterId) {
        List<RentalRequest> rentalRequests;

        if (bikeId != null && requesterId != null) {
            rentalRequests = rentalRequestRepository.findAllByBikeIdAndBikeRequesterId(bikeId, requesterId);
        } else if (bikeId == null) {
            rentalRequests = requesterId == null ? rentalRequestRepository.findAll()
                    : rentalRequestRepository.findAllByRequesterId(requesterId);
        } else {
            rentalRequests = rentalRequestRepository.findAllByBikeId(bikeId);
        }

        return rentalRequests.stream().map(rentalRequest -> toRentalRequestDto(rentalRequest))
                .collect(Collectors.toList());
    }

    public void addRentalRequest(RentalRequestDto rentalRequestDto) {
        rentalRequestDto.setStatus("sent");
        rentalRequestRepository.save(toRentalRequest(rentalRequestDto));
    }

    public RentalRequestDto cancelRequest(String id, RentalRequestDto rentalRequestDto) throws Exception {
        rentalRequestDto.setId(id);
        RentalRequest rentalRequest = this.rentalRequestRepository.findById(id).orElse(null);

        if (rentalRequest == null) {
            throw new NotFoundException();
        }

        if (!rentalRequest.getStatus().equals("cancelled")) {
            rentalRequest.setStatus(rentalRequestDto.getStatus());

            rentalRequestRepository.save(rentalRequest);

            return toRentalRequestDto(rentalRequest);
        }

        return null;
    }

    public RentalRequestDto toRentalRequestDto(RentalRequest rentalRequest) {
        return new RentalRequestDto(rentalRequest.getId(), rentalRequest.getBikeOwnerId(),
                rentalRequest.getBikeRequesterId(),
                rentalRequest.getBikeId(), rentalRequest.getStatus(), rentalRequest.getStringStartDate(),
                rentalRequest.getStringEndDate());
    }

    public RentalRequest toRentalRequest(RentalRequestDto rentalRequestDto) {
        return new RentalRequest(rentalRequestDto.getBikeOwnerId(), rentalRequestDto.getBikeRequesterId(),
                rentalRequestDto.getBikeId(), rentalRequestDto.getStatus(), rentalRequestDto.getStartDate(),
                rentalRequestDto.getEndDate());
    }

    public RentalApprovalDto toRentalApprovalDto(RentalApproval rentalApproval) {
        return new RentalApprovalDto(rentalApproval.getId(), rentalApproval.getRequestId(),
                rentalApproval.getApprovalStatus(),
                rentalApproval.getDetails());
    }
}

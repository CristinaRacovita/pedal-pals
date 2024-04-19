package soa.group11.rentalService.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soa.group11.rentalService.entities.RentalApproval;
import soa.group11.rentalService.entities.RentalRequest;
import soa.group11.rentalService.models.RentalApprovalDto;
import soa.group11.rentalService.repositories.RentalApprovalRepository;
import soa.group11.rentalService.repositories.RentalRequestRepository;

@Service
public class RentalApprovalService {
    @Autowired
    private RentalApprovalRepository rentalApprovalRepository;

    @Autowired
    private RentalRequestRepository rentalRequestRepository;

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

    public boolean getRentalAvailability(String bikeId, String startDate, String endDate) {
        List<RentalRequest> rentalRequests = rentalRequestRepository.findAllByBikeId(bikeId);

        for (RentalRequest rentalRequest : rentalRequests) {
            if (rentalRequest.getStatus().equals("sent")
                    && startDate.compareTo(rentalRequest.getStringEndDate().split(" ")[0]) <= 0
                    && endDate.compareTo(rentalRequest.getStringStartDate().split(" ")[0]) >= 0) {
                List<RentalApproval> rentalApprovals = rentalApprovalRepository
                        .findAllByRequestId(rentalRequest.getId());

                for (RentalApproval rentalApproval : rentalApprovals) {
                    if (rentalApproval.getApprovalStatus().equals("approved")) {
                        return false;
                    }
                }
            }
        }

        return true;
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

package soa.group11.rentalService.services;

import java.util.List;
import java.util.Optional;
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

    public boolean addApprovalStatus(RentalApprovalDto rentalApprovalDto) {
        RentalRequest rentalRequest = rentalRequestRepository.findById(rentalApprovalDto.getRequestId()).orElse(null);

        if (rentalRequest == null) {
            return false;
        }

        if (rentalApprovalDto.getApprovalStatus().equals("approved")
                || rentalApprovalDto.getApprovalStatus().equals("declined")) {
            rentalApprovalRepository.save(toRentalApproval(rentalApprovalDto));
            return true;
        }

        return false;
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

package soa.group11.rentalService.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import soa.group11.rentalService.models.RentalRequestDto;

@Validated
@RestController
public class BikeApprovalController {
    @Autowired
    private BikeApprovalService bikeApprovalService;

    @Autowired
    private BikeApprovalProducer bikeApprovalProducer;

    @GetMapping("/approvals")
    public List<RentalApprovalDto> getApprovalStatuses(){
        return bikeApprovalService.getApprovalStatuses();
    }

    @PostMapping("/approve")
    public void handleRequest(@RequestBody @Valid RentalApprovalDto rentalApprovalDto){
        bikeApprovalService.addApprovalStatus(rentalApprovalDto);
    }
}

package soa.group11.rentalService.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import soa.group11.rentalService.entities.RentalRequest;
import soa.group11.rentalService.models.RentalApprovalDto;
import soa.group11.rentalService.producers.RentalApprovalProducer;
import soa.group11.rentalService.services.RentalApprovalService;

@Validated
@RestController
public class RentalApprovalController {
    @Autowired
    private RentalApprovalService rentalApprovalService;

    @Autowired
    private RentalApprovalProducer rentalApprovalProducer;

    @GetMapping("/approvals")
    public List<RentalApprovalDto> getApprovalStatuses() {
        return rentalApprovalService.getApprovalStatuses();
    }

    @PostMapping("/approve")
    public ResponseEntity<HttpStatus> handleRequest(@RequestBody @Valid RentalApprovalDto rentalApprovalDto) {
        RentalRequest rentalRequest = rentalApprovalService.addApprovalStatus(rentalApprovalDto);

        if (rentalRequest == null)
            return new ResponseEntity<HttpStatus>(HttpStatus.BAD_REQUEST);

        rentalApprovalProducer.sendApprovalStatus(rentalApprovalDto, rentalRequest);
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }

    @GetMapping("/approvals/available/{bikeId}")
    public boolean getRentalAvailability(@PathVariable String bikeId, @RequestParam(name = "startDate") String startDate,
            @RequestParam(name = "endDate") String endDate) {
        return rentalApprovalService.getRentalAvailability(bikeId, startDate, endDate);
    }
}

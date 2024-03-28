package soa.group11.rentalService.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import soa.group11.rentalService.models.RentalRequestDto;
import soa.group11.rentalService.producers.RentalRequestProducer;
import soa.group11.rentalService.services.RentalRequestService;

@Validated
@RestController
public class RentalRequestController {
    @Autowired
    private RentalRequestService rentalRequestService;

    @Autowired
    private RentalRequestProducer rentalRequestProducer;

    @GetMapping("/requests")
    public ResponseEntity<List<RentalRequestDto>> getRequests(
            @RequestParam(value = "bikeId", required = false) String bikeId,
            @RequestParam(value = "requesterId", required = false) String requesterId) {

        List<RentalRequestDto> requests = rentalRequestService.getRequests(bikeId, requesterId);

        if (requests.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @PostMapping("/request")
    public void addRentalRequest(@RequestBody @Valid RentalRequestDto rentalRequestDto) {
        rentalRequestService.addRentalRequest(rentalRequestDto);
        rentalRequestProducer.sendRequest(rentalRequestDto);
    }

    @PatchMapping("/request/{id}")
    public ResponseEntity<RentalRequestDto> cancelRequest(@PathVariable String id,
            @RequestBody RentalRequestDto rentalRequestDto) {
        if (!rentalRequestDto.getStatus().equals("cancelled")) {
            System.out.println(rentalRequestDto.getStatus());
            return new ResponseEntity<RentalRequestDto>(HttpStatus.BAD_REQUEST);
        }

        try {
            RentalRequestDto updatedRentalRequestDto = rentalRequestService.cancelRequest(id, rentalRequestDto);

            if (updatedRentalRequestDto == null) {
                return new ResponseEntity<RentalRequestDto>(HttpStatus.BAD_REQUEST);
            }
        
            rentalRequestProducer.sendRequest(updatedRentalRequestDto);
            return ResponseEntity.ok(updatedRentalRequestDto);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<RentalRequestDto>(HttpStatus.NOT_FOUND);
        }
    }
}

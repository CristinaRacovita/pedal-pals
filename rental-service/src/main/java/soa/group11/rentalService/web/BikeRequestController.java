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
import soa.group11.rentalService.services.BikeRequestService;

@Validated
@RestController
public class BikeRequestController {
    @Autowired
    private BikeRequestService bikeRequestService;

    @Autowired
    private RentalRequestProducer bikeRequestProducer;

    @GetMapping("/requests")
    public ResponseEntity<List<RentalRequestDto>> getRequests(
            @RequestParam(value = "bikeId", required = false) String bikeId,
            @RequestParam(value = "requesterId", required = false) String requesterId) {

        List<RentalRequestDto> requests = bikeRequestService.getRequests(bikeId, requesterId);

        if (requests.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @PostMapping("/request")
    public void addBikeRequest(@RequestBody @Valid RentalRequestDto bikeRequestDto) {
        bikeRequestService.addRentalRequest(bikeRequestDto);
        bikeRequestProducer.sendRequest(bikeRequestDto);
    }

    @PatchMapping("/request/{id}")
    public ResponseEntity<RentalRequestDto> cancelRequest(@PathVariable String id,
            @RequestBody RentalRequestDto bikeRequestDto) {
        if (!bikeRequestDto.getStatus().equals("cancelled")) {
            System.out.println(bikeRequestDto.getStatus());
            return new ResponseEntity<RentalRequestDto>(HttpStatus.BAD_REQUEST);
        }

        try {
            RentalRequestDto updatedBikeRequestDto = bikeRequestService.cancelRequest(id, bikeRequestDto);

            if (updatedBikeRequestDto == null) {
                return new ResponseEntity<RentalRequestDto>(HttpStatus.BAD_REQUEST);
            }
        
            bikeRequestProducer.sendRequest(updatedBikeRequestDto);
            return ResponseEntity.ok(updatedBikeRequestDto);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<RentalRequestDto>(HttpStatus.NOT_FOUND);
        }
    }
}

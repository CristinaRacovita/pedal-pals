package soa.group11.rentalRequestService.web;

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
import soa.group11.rentalRequestService.services.BikeRequestService;

import soa.group11.rentalRequestService.models.BikeRequestDto;
import soa.group11.rentalRequestService.producers.BikeRequestProducer;

@Validated
@RestController
public class BikeRequestController {
    @Autowired
    private BikeRequestService bikeRequestService;

    @Autowired
    private BikeRequestProducer bikeRequestProducer;

    @GetMapping("/requests")
    public ResponseEntity<List<BikeRequestDto>> getRequestsByRequesterId(
            @RequestParam(value = "bikeId", required = false) String bikeId,
            @RequestParam(value = "requesterId", required = false) String requesterId) {

        List<BikeRequestDto> requests = bikeRequestService.getRequests(bikeId, requesterId);

        if (requests.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @PostMapping("/request")
    public void addBikeRequest(@RequestBody @Valid BikeRequestDto bikeRequestDto) {
        bikeRequestService.addBikeRequest(bikeRequestDto);
        bikeRequestProducer.sendRequest(bikeRequestDto);
    }

    @PatchMapping("/request/{id}")
    public ResponseEntity<BikeRequestDto> cancelRequest(@PathVariable String id,
            @RequestBody BikeRequestDto bikeRequestDto) {
        if (!bikeRequestDto.getStatus().equals("cancelled")) {
            System.out.println(bikeRequestDto.getStatus());
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        try {
            BikeRequestDto updatedBikeRequestDto = bikeRequestService.cancelRequest(id, bikeRequestDto);

            if (updatedBikeRequestDto == null) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            } else {
                bikeRequestProducer.sendRequest(updatedBikeRequestDto);
                return ResponseEntity.ok(updatedBikeRequestDto);
            }
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}

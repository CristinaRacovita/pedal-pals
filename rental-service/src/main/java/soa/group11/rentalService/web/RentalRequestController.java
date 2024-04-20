package soa.group11.rentalService.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    private static final String CANCELLED = "cancelled";

    @Autowired
    private RentalRequestService rentalRequestService;

    @Autowired
    private RentalRequestProducer rentalRequestProducer;

    @GetMapping("/requests")
    public ResponseEntity<List<RentalRequestDto>> getRequests(
            @RequestParam(value = "bikeId", required = false) String bikeId,
            @RequestParam(value = "requesterId", required = false) int requesterId) {

        List<RentalRequestDto> requests = rentalRequestService.getRequests(bikeId, requesterId);

        if (requests.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(requests, HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/request")
    public ResponseEntity<HttpStatus> addRentalRequest(@RequestBody @Valid RentalRequestDto rentalRequestDto) {
        int comparison = rentalRequestDto.getStartDate().compareTo(rentalRequestDto.getEndDate());

        if (comparison < 0) {
            rentalRequestService.addRentalRequest(rentalRequestDto);
            rentalRequestProducer.sendRequest(rentalRequestDto);
            return new ResponseEntity<HttpStatus>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PatchMapping("/requests/{id}")
    public ResponseEntity<RentalRequestDto> cancelRequest(@PathVariable String id,
            @RequestBody RentalRequestDto rentalRequestDto) {

        System.out.println("INSIDE!!");
        if (!rentalRequestDto.getStatus().equals(CANCELLED)) {
            return new ResponseEntity<RentalRequestDto>(HttpStatus.BAD_REQUEST);
        }

        try {
            RentalRequestDto updatedRentalRequestDto = rentalRequestService.cancelRequest(id, rentalRequestDto);

            if (updatedRentalRequestDto == null) {
                return new ResponseEntity<RentalRequestDto>(HttpStatus.BAD_REQUEST);
            }

            rentalRequestProducer.sendRequest(updatedRentalRequestDto);
            return ResponseEntity.ok(updatedRentalRequestDto);

        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<RentalRequestDto>(HttpStatus.NOT_FOUND);
        }
    }
}

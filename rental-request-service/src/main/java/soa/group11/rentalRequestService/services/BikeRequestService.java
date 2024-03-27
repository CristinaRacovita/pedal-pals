package soa.group11.rentalRequestService.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soa.group11.rentalRequestService.entities.BikeRequest;
import soa.group11.rentalRequestService.models.BikeRequestDto;
import soa.group11.rentalRequestService.repositories.BikeRequestRepository;

@Service
public class BikeRequestService {
    @Autowired
    private BikeRequestRepository bikeRequestRepository;


    public List<BikeRequestDto> getRequests(String bikeId, String requesterId) {
        List<BikeRequest> bikeRequests;

        if (bikeId != null && requesterId != null){
            bikeRequests = bikeRequestRepository.findAllByBikeIdAndBikeRequesterId(bikeId, requesterId);
        } else if (bikeId == null && requesterId == null) {
            bikeRequests = bikeRequestRepository.findAll();
        } else {
            if (bikeId == null) {
                bikeRequests = bikeRequestRepository.findAllByRequesterId(requesterId);
            } else {
                bikeRequests = bikeRequestRepository.findAllByBikeId(bikeId);
            }
        }

        return bikeRequests.stream().map(bikeRequest -> toBikeRequestDto(bikeRequest))
                .collect(Collectors.toList());
    }

    public void addBikeRequest(BikeRequestDto bikeRequestDto) {
        bikeRequestDto.setStatus("sent");
        bikeRequestRepository.save(toBikeRequest(bikeRequestDto));
    }

    public BikeRequestDto cancelRequest(String id, BikeRequestDto bikeRequestDto) throws Exception {
        // make sure the object matches the path variable
        bikeRequestDto.setId(id);
        BikeRequest bikeRequest = this.bikeRequestRepository.findById(id).orElse(null);

        if (bikeRequest != null) {
            // only the status can be updated

            if (!bikeRequest.getStatus().equals("cancelled")){
                bikeRequest.setStatus(bikeRequestDto.getStatus());

                bikeRequestRepository.save(bikeRequest);
    
                return toBikeRequestDto(bikeRequest);
            }else{
                return null;
            }
        } else {
            throw new Exception("Bike request with ID " + id + " not found");
        }
    }

    public BikeRequestDto toBikeRequestDto(BikeRequest bikeRequest) {
        return new BikeRequestDto(bikeRequest.getId(), bikeRequest.getBikeOwnerId(), bikeRequest.getBikeRequesterId(),
                bikeRequest.getBikeId(), bikeRequest.getStatus());
    }

    public BikeRequest toBikeRequest(BikeRequestDto bikeRequestDto) {
        return new BikeRequest(bikeRequestDto.getBikeOwnerId(), bikeRequestDto.getBikeRequesterId(),
                bikeRequestDto.getBikeId(), bikeRequestDto.getStatus());
    }
}

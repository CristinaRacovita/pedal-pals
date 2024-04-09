package soa.group11.bikeManagementService.services;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import soa.group11.bikeManagementService.entities.Bike;
import soa.group11.bikeManagementService.entities.BikeImage;
import soa.group11.bikeManagementService.repositories.BikeRepository;

@Service
public class BikeImageService {
    @Autowired
    private BikeRepository bikeRepository;

    public String uploadImage(MultipartFile file, String bikeId) {
        try {
            Optional<Bike> bikeOptional = bikeRepository.findById(bikeId);
            if (!bikeOptional.isPresent()) {
                return null;
            }

            Bike bike = bikeOptional.get();

            BikeImage image = new BikeImage();
            image.setData(file.getBytes());
            image.setContentType(file.getContentType());

            bike.setBikeImage(image);
            return bikeRepository.save(bike).getBikeImage().getId();
        } catch (IOException ex) {
            return ex.getMessage();
        }
    }

    public ResponseEntity<byte[]> getImage(String bikeId) {
        Optional<Bike> bikeOptional = bikeRepository.findById(bikeId);
        if (bikeOptional.isPresent()) {
            BikeImage image = bikeOptional.get().getBikeImage();
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(image.getContentType()))
                    .body(image.getData());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

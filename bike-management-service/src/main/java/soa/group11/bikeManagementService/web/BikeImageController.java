package soa.group11.bikeManagementService.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import soa.group11.bikeManagementService.services.BikeImageService;

@RestController
public class BikeImageController {
    @Autowired
    public BikeImageService bikeImageService;

    @PostMapping("/upload/{bikeId}")
    public String handleFileUpload(@RequestPart("file") MultipartFile file,
            @PathVariable(value = "bikeId") String bikeId) {
        return bikeImageService.uploadImage(file, bikeId);
    }
}

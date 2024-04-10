package soa.group11.bikeManagementService.entities;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BikeImage {
    @Id
    private String id;
    private byte[] data;
    private String contentType;

    public BikeImage() {
    }
}

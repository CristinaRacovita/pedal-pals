package soa.group11.notificationService.consumers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import soa.group11.notificationService.entities.RequestNotification;
import soa.group11.notificationService.models.RentalRequestDto;
import soa.group11.notificationService.repositories.RequestNotificationRepository;

@Component
public class NewRequestConsumer {
    @Autowired
    private RequestNotificationRepository requestNotificationRepository;

    @JmsListener(destination = "${queue.request}")
    public void receiveRequest(String requestJson) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            RentalRequestDto rentalRequest = objectMapper.readValue(requestJson, RentalRequestDto.class);

            System.out.println(rentalRequest.toString());
            requestNotificationRepository
                    .save(new RequestNotification(rentalRequest.getBikeOwnerId(), rentalRequest.toString()));
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
    }
}

package main.java.soa.group11.notificationService.consumers;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import main.java.soa.group11.notificationService.models.BikeRequest;


@Component
public class NewRequestConsumer {
    @JmsListener(destination = "${queue.request}")
    public void receiveRequest(String requestJson){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            // ignore unknown fields
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            
            BikeRequest bikeRequest = objectMapper.readValue(requestJson, BikeRequest.class);

            System.console().printf("User %s has received the following bike request\n %s", bikeRequest.getBikeOwnerId(), bikeRequest.toString());
        }catch(JsonProcessingException e){
            e.printStackTrace();
        }
    }
}

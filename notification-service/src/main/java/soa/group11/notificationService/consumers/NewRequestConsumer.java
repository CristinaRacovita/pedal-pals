package soa.group11.notificationService.consumers;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import soa.group11.notificationService.models.BikeRequest;


@Component
public class NewRequestConsumer {
    @JmsListener(destination = "${queue.request}")
    public void receiveRequest(String requestJson){
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            // ignore unknown fields
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            
            BikeRequest bikeRequest = objectMapper.readValue(requestJson, BikeRequest.class);

            System.out.println(bikeRequest.toString());
        }catch(JsonProcessingException e){
            //e.printStackTrace();
            System.out.println("Error");
        }
    }
}

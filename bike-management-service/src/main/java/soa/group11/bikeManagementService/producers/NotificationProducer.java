package soa.group11.bikeManagementService.producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import soa.group11.bikeManagementService.models.BikeDto;
import soa.group11.bikeManagementService.models.BikeSubscriptionDto;

@Component
public class NotificationProducer {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${queue.notification}")
    private String notificationQueue;

    public void sendMessage(BikeDto bike) {
        try {
            // TODO: send real data
            BikeSubscriptionDto bikeSubscriptionDto = new BikeSubscriptionDto("Cristina", 27, 1, "2022-04-09 12:00", "2023-01-10 11:00", "Union", "Regular", "City");

            ObjectMapper mapper = new ObjectMapper();
            String bikeJson = mapper.writeValueAsString(bikeSubscriptionDto);
            jmsTemplate.convertAndSend(notificationQueue, bikeJson);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

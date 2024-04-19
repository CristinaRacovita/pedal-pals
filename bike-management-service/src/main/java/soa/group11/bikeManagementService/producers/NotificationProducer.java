package soa.group11.bikeManagementService.producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import soa.group11.bikeManagementService.models.BikeSubscriptionDto;
import soa.group11.bikeManagementService.models.NewBikeDto;

@Component
public class NotificationProducer {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${queue.notification}")
    private String notificationQueue;

    public void sendMessage(NewBikeDto bike) {
        try {
            BikeSubscriptionDto bikeSubscriptionDto = new BikeSubscriptionDto(bike.getUserId(),
                    bike.getWheelSize(), bike.getNumberOfGears(),
                    bike.getStartRentingDate(), bike.getEndRentingDate(),
                    bike.getBrand(), bike.getType(), bike.getSuitability());

            ObjectMapper mapper = new ObjectMapper();
            String bikeJson = mapper.writeValueAsString(bikeSubscriptionDto);
            jmsTemplate.convertAndSend(notificationQueue, bikeJson);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

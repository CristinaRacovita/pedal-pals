package soa.group11.bikeManagementService.producers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import soa.group11.bikeManagementService.models.BikeSubscriptionDto;

@Component
public class NewSubscriptionProducer {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${queue.new-subscription}")
    private String newSubscriptionQueue;

    public void sendMessage(BikeSubscriptionDto bike) {
        if (bike.getStartDate().equals("")) {
            bike.setStartDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        }

        if (bike.getEndDate().equals("")) {
            bike.setEndDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        }

        if (bike.getBrand().equals("")) {
            bike.setBrand("All brands");
        }

        if (bike.getType().equals("")) {
            bike.setType("All types");
        }

        if (bike.getUsage().equals("")) {
            bike.setUsage("All usages");
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            String bikeJson = mapper.writeValueAsString(bike);
            jmsTemplate.convertAndSend(newSubscriptionQueue, bikeJson);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}

package soa.group11.notificationService.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

import org.springframework.data.annotation.Id;
import lombok.Setter;
import lombok.Getter;

@Document(collection = "bike_subscriptions")
@Getter
@Setter
public class Subscription {
    @Id
    private BigInteger  id;
    private int userId;
    private int numberOfGears;
    private String brand;

    public Subscription(BigInteger id, int userId, int numberOfGears, String brand){
        this.brand = brand;
        this.id = id;
        this.numberOfGears = numberOfGears;
        this.userId = userId;
    }
}

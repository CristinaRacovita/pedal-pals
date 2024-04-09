package soa.group11.notificationService.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Setter;
import lombok.Getter;

@Document(collection = "request_notifications")
@Getter
@Setter
public class RequestNotification {
    private String ownerId;
    private String text;
    private String notificationDate;

    public RequestNotification() {

    }

    public RequestNotification(String ownerId, String text) {
        this.ownerId = ownerId;
        this.text = text;
        this.notificationDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
    }

    public RequestNotification(String ownerId, String text, String notificationDate) {
        this.ownerId = ownerId;
        this.text = text;
        this.notificationDate = notificationDate;
    }
}

package soa.group11.notificationService.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "notifications")
@Getter
@Setter
public class Notification {
    private int notifiedUserId;
    private String type;
    private String text;
    private String checkDate;
    private String notificationDate;

    public Notification() {

    }

    public Notification(int notifiedUserId, String type, String text, String checkDate) {
        this.notifiedUserId = notifiedUserId;
        this.type = type;
        this.text = text;
        this.checkDate = checkDate;
        this.notificationDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
    }

    public Notification(int notifiedUserId, String type, String text, String notificationDate, String checkDate) {
        this.notifiedUserId = notifiedUserId;
        this.type = type;
        this.text = text;
        this.notificationDate = notificationDate;
        this.checkDate = checkDate;
    }
}
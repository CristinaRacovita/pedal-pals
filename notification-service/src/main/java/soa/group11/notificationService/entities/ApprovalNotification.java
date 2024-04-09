package soa.group11.notificationService.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Document(collection = "approval_notifications")
@Getter
@Setter
public class ApprovalNotification {
    private String requesterId;
    private String text;
    private String notificationDate;

    public ApprovalNotification() {

    }

    public ApprovalNotification(String requesterId, String text) {
        this.requesterId = requesterId;
        this.text = text;
        this.notificationDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
    }

    public ApprovalNotification(String requesterId, String text, String notificationDate) {
        this.requesterId = requesterId;
        this.text = text;
        this.notificationDate = notificationDate;
    }
}

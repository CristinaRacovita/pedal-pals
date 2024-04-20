package soa.group11.notificationService.services;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soa.group11.notificationService.entities.Notification;
import soa.group11.notificationService.repositories.NotificationRepository;

@Service
public class NotificationService {
    @Autowired
    NotificationRepository notificationRepository;

    public List<Notification> getNotifications(int userId) {
        List<Notification> notifications = notificationRepository.findByNotifiedUserId(userId);

        String currentDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
        List<Notification> dueNotifications = notificationRepository.findDueNotificationsLike(userId,
                "approved_request", currentDateTime);

        if (!dueNotifications.isEmpty()) {
            for (Notification notification : dueNotifications) {
                String notificationText = notification.getText();
                String bikeName = notificationText.substring(17, notificationText.indexOf(" has been "));
                String feedbackNotificationText = "The rental period for bike " + bikeName + " has ended on "
                        + notification.getCheckDate() + ". Please add a feedback.";

                Notification feedNotification = new Notification(userId, "feedback", feedbackNotificationText, null);

                boolean alreadyStored = false;
                for (Notification existingNotification : notifications) {
                    if (existingNotification.getText().equals(feedbackNotificationText)) {
                        alreadyStored = true;
                    }

                }

                if (!alreadyStored) {
                    notificationRepository.save(feedNotification);
                    notifications.add(feedNotification);
                }
            }
        }

        notifications.sort(Comparator.comparing(Notification::getNotificationDate).reversed());

        return notifications;
    }
}

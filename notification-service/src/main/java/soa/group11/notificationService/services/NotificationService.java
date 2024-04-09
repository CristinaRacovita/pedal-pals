package soa.group11.notificationService.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soa.group11.notificationService.entities.ApprovalNotification;
import soa.group11.notificationService.entities.RequestNotification;
import soa.group11.notificationService.repositories.ApprovalNotificationRepository;
import soa.group11.notificationService.repositories.RequestNotificationRepository;

@Service
public class NotificationService {
    @Autowired
    RequestNotificationRepository requestNotificationRepository;
    @Autowired
    ApprovalNotificationRepository approvalNotificationRepository;

    public List<String> getNotifications(String userId) {
        List<String> notifications = new ArrayList<>();

        List<RequestNotification> requestNotifications = requestNotificationRepository.findByOwnerId(userId);
        List<ApprovalNotification> approvalNotifications = approvalNotificationRepository.findByRequesterId(userId);

        if (requestNotifications != null) {
            for (RequestNotification requestNotification : requestNotifications) {
                notifications.add(requestNotification.getText());
            }
        }

        if (approvalNotifications != null) {
            for (ApprovalNotification approvalNotification : approvalNotifications) {
                notifications.add(approvalNotification.getText());
            }
        }

        return notifications;
    }
}

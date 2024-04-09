package soa.group11.notificationService.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soa.group11.notificationService.entities.ApprovalNotification;
import soa.group11.notificationService.entities.RequestNotification;
import soa.group11.notificationService.models.NotificationDto;
import soa.group11.notificationService.repositories.ApprovalNotificationRepository;
import soa.group11.notificationService.repositories.RequestNotificationRepository;

@Service
public class NotificationService {
    @Autowired
    RequestNotificationRepository requestNotificationRepository;
    @Autowired
    ApprovalNotificationRepository approvalNotificationRepository;

    public List<NotificationDto> getNotifications(String userId) {
        List<NotificationDto> notifications = new ArrayList<>();

        List<RequestNotification> requestNotifications = requestNotificationRepository.findByOwnerId(userId);
        List<ApprovalNotification> approvalNotifications = approvalNotificationRepository.findByRequesterId(userId);

        String notificationType;

        if (requestNotifications != null) {
            for (RequestNotification requestNotification : requestNotifications) {
                if (requestNotification.getText().contains("cancelled")) {
                    notificationType = "cancelled_request";
                } else {
                    notificationType = "sent_request";
                }
                notifications.add(new NotificationDto(notificationType, requestNotification.getText(),
                        requestNotification.getNotificationDate()));
            }
        }

        if (approvalNotifications != null) {
            for (ApprovalNotification approvalNotification : approvalNotifications) {
                if (approvalNotification.getText().contains("approved")) {
                    notificationType = "approved_request";
                } else {
                    notificationType = "declined_request";
                }

                notifications.add(new NotificationDto(notificationType, approvalNotification.getText(),
                        approvalNotification.getNotificationDate()));
            }
        }

        notifications.sort(Comparator.comparing(NotificationDto::getDate).reversed());

        for(NotificationDto notificationDto: notifications){
            System.out.println(notificationDto.getType());
        }

        return notifications;
    }
}

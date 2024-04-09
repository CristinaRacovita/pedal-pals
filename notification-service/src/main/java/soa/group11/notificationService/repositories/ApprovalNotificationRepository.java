package soa.group11.notificationService.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import soa.group11.notificationService.entities.ApprovalNotification;

@Repository
public interface ApprovalNotificationRepository extends MongoRepository<ApprovalNotification, String> {
    List<ApprovalNotification> findByRequesterId(String requesterId);
}
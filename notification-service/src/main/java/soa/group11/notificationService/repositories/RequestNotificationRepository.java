package soa.group11.notificationService.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import soa.group11.notificationService.entities.RequestNotification;

@Repository
public interface RequestNotificationRepository extends MongoRepository<RequestNotification, String> {
    List<RequestNotification> findByOwnerId(String ownerId);
}

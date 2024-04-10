package soa.group11.notificationService.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import soa.group11.notificationService.entities.Notification;

@Repository
public interface NotificationRepository extends MongoRepository<Notification, String> {
    List<Notification> findByNotifiedUserId(String notifiedUserId);

    @Query(value = "{'notifiedUserId': ?0, 'type': ?1, 'text': ?2}")
    List<Notification> findNotificationsLike(String notifiedUserId, String type, String text);

    @Query(value = "{'notifiedUserId': ?0, 'type': ?1, 'checkDate' : {$lte: ?2}}")
    List<Notification> findDueNotificationsLike(String notifiedUserId, String type, String checkDate);
}

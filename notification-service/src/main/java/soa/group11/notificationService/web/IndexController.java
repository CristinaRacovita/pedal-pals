package soa.group11.notificationService.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import soa.group11.notificationService.services.NotificationService;

@Controller
public class IndexController {
    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notifications/{userId}")
    public String getNotificationsByUserId(Model model, @PathVariable(value = "userId") String userId) {
        model.addAttribute("userId", userId);
        model.addAttribute("notifications", notificationService.getNotifications(userId));

        return "notifications_overview";
    }
}

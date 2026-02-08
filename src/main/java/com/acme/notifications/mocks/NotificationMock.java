package com.acme.notifications.mocks;

import com.acme.notifications.domains.Channel;
import com.acme.notifications.domains.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationMock {

    public static Notification createNotification() {
        return new Notification(Channel.NOT_DEFINED, "user@example.com", "This is a test notification.", "Welcome!");
    }

    public static List<Notification> createNotifications() {
        List<Notification> notifications = new ArrayList<>();
        Notification notification =
                new Notification(Channel.EMAIL, "user@example.com", "This is a EMAIL notification test.", "Welcome!");
        notifications.add(notification);

        notification =
                new Notification(Channel.EMAIL, "user2@example.com", "This is a EMAIL notification test.", "Welcome!");
        notifications.add(notification);

        notification =
                new Notification(Channel.PUSH, "user@example.com", "This is a PUSH notification test.", "Welcome!");
        notifications.add(notification);

        notification =
                new Notification(Channel.PUSH, "user2@example.com", "This is a PUSH notification test.", "Welcome!");
        notifications.add(notification);

        notification =
                new Notification(Channel.SMS, "+525552458801", "This is a SMS notification test.", "Welcome!");
        notifications.add(notification);

        notification =
                new Notification(Channel.SMS, "+525567458801", "This is a SMS notification test.", "Welcome!");
        notifications.add(notification);

        return notifications;
    }
}

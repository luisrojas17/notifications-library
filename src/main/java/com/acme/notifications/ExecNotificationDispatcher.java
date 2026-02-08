package com.acme.notifications;

import com.acme.notifications.dispatchers.NotificationDispatcher;
import com.acme.notifications.helpers.NotificationHelper;
import com.acme.notifications.mocks.NotificationMock;
import com.acme.notifications.providers.impl.FirebaseProvider;
import com.acme.notifications.providers.impl.SendGridProvider;
import com.acme.notifications.domains.Channel;
import com.acme.notifications.domains.Notification;
import com.acme.notifications.providers.NotificationProvider;
import com.acme.notifications.providers.impl.TwilioProvider;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class ExecNotificationDispatcher {

    public static final String DISPATCH = "dispatch";
    public static final String DISPATCHAll = "dispatchAll";

    ExecutorService executorService =
            Executors.newFixedThreadPool(5);

    public static void main(String... args) {

        Scanner scanner = new Scanner(System.in);
        log.warn("You have to provide one option to simulate sending notifications." +
                "\n The possible options are:" +
                "\n\t - dispatch: To dispatch just one notification using SendGridProvider and FarebaseProvider ." +
                "\n\t - dispatchAll: To dispatch a list of notifications using SendGridProvider, FarebaseProvider and TwilioProvider.");

        String input = scanner.nextLine();

        ExecNotificationDispatcher execNotificationDispatcher =
                new ExecNotificationDispatcher();

        switch (input) {
            case DISPATCH:
                // To dispatch just one notification
                execNotificationDispatcher.dispatch();
                break;
            case DISPATCHAll:
                // To dispatch a list of notifications
                execNotificationDispatcher.dispatchAll();
                break;
            default:
                log.error("Invalid option, bye!!!");
        }

        scanner.close();

    }

    public void dispatch() {

        List<NotificationProvider> providers = List.of(
                new SendGridProvider(executorService),
                new FirebaseProvider(executorService)
        );

        NotificationDispatcher dispatcher =
                new NotificationDispatcher(providers);

        Notification notification = NotificationMock.createNotification();

        dispatcher.dispatch(
                notification,
                Set.of(Channel.EMAIL, Channel.PUSH, Channel.SMS)
        ).thenAccept(NotificationHelper::printResults).join();
    }

    public void dispatchAll() {

        List<NotificationProvider> providers = List.of(
                new SendGridProvider(executorService),
                new FirebaseProvider(executorService),
                new TwilioProvider(executorService)
        );

        NotificationDispatcher dispatcher =
                new NotificationDispatcher(providers);

        List<Notification> notifications = NotificationMock.createNotifications();

        dispatcher.dispatchAll(
                notifications
        ).thenAccept(NotificationHelper::printResults).join();

    }

}

package com.acme.notifications.dispatchers;

import com.acme.notifications.BaseTest;
import com.acme.notifications.domains.Channel;
import com.acme.notifications.domains.Notification;
import com.acme.notifications.helpers.NotificationHelper;
import com.acme.notifications.mocks.NotificationMock;
import com.acme.notifications.providers.NotificationProvider;
import com.acme.notifications.providers.impl.FirebaseProvider;
import com.acme.notifications.providers.impl.SendGridProvider;
import com.acme.notifications.providers.impl.TwilioProvider;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

@Slf4j
class NotificationDispatcherTest extends BaseTest {

    @Test
    void testDispatchSuccess() {

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

    @Test
    void testDispatchAllSuccess() {

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

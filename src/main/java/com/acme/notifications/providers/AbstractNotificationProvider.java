package com.acme.notifications.providers;

import com.acme.notifications.domains.Notification;
import com.acme.notifications.domains.Result;
import com.acme.notifications.helpers.NotificationHelper;
import com.acme.notifications.rules.ConfigurationRules;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

/**
 * Abstract class for notification providers.
 *
 * @author Jose Luis Rojas Gomez
 */
public abstract class AbstractNotificationProvider implements NotificationProvider {

    protected final ConfigurationRules configurationRules =
            new ConfigurationRules();

    protected ExecutorService executorService;

    @Override
    public String name() {
        return this.getClass().getSimpleName();
    }

    @Override
    public CompletableFuture<Result> sendAsync(final Notification notification) {
        return CompletableFuture.supplyAsync(() -> {
            try {

                sendSync(notification);

                return NotificationHelper.buildSuccessResult(name());
            } catch (Exception e) {
                return NotificationHelper.buildFailureResult(name(), e.getMessage());
            }
        }, executorService);
    }

    public List<CompletableFuture<Result>> sendAsync(final List<Notification> notifications) {

        return notifications.stream()
                .map(notification -> CompletableFuture.supplyAsync(() -> {

                    try {

                        sendSync(notification);

                        return NotificationHelper.buildSuccessResult(name());
                    } catch (Exception e) {
                        return NotificationHelper.buildFailureResult(name(), e.getMessage());
                    }

                }, executorService))
                .collect(Collectors.toList());

    }

    public abstract void sendSync(final Notification notification) throws Exception;

}

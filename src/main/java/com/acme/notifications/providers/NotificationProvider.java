package com.acme.notifications.providers;

import com.acme.notifications.domains.Channel;
import com.acme.notifications.domains.Notification;
import com.acme.notifications.domains.Result;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Interface for notification providers which define the contract for handling
 * sending notifications to different channels.
 *
 * @author Jose Luis Rojas Gomez
 */
public interface NotificationProvider {
    String name();
    Channel getChannel();
    void sendSync(Notification notification) throws Exception;
    CompletableFuture<Result> sendAsync(Notification notification);
    List<CompletableFuture<Result>> sendAsync(List<Notification> notifications);
}

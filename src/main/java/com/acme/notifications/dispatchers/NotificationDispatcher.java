package com.acme.notifications.dispatchers;

import com.acme.notifications.domains.Channel;
import com.acme.notifications.domains.Notification;
import com.acme.notifications.domains.Result;
import com.acme.notifications.providers.NotificationProvider;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * This class is responsible to dispatch the notifications. In other words, this is the entrypoint to send notifications.
 * <br/>
 * The rules to consider are next:
 * <ul>
 *     <li>The notification has to be sent to the channels provided.</li>
 *     <li>The notification has to be sent to the providers configured.</li>
 *     <li>notification's channel has to be the same as provider's channel.</li>
 * </ul>
 *
 * @author Jose Luis Rojas Gomez
 */
@Slf4j
public class NotificationDispatcher {

    private final List<NotificationProvider> providers;

    public NotificationDispatcher(List<NotificationProvider> providers) {
        this.providers = providers;
    }

    /**
     * To send the notification to all channels provided. The channels are defined by provider.
     *
     * @param notification the notification to send.
     *
     * @param channels the channels who the notification has to be sent.
     *
     * @return a list of results.
     */
    public CompletableFuture<List<Result>> dispatch(
            final Notification notification,
            final  Set<Channel> channels) {

        if (Objects.isNull(channels) || channels.isEmpty()) {
            throw new IllegalArgumentException("Channels cannot be null or empty.");
        }

        // To send the notifications according to channels given
        List<CompletableFuture<Result>> futures =
                providers.stream()
                        .filter(p -> channels.contains(p.getChannel()))
                        .map(p -> p.sendAsync(notification))
                        .toList();

        // To join all futures because the logic has could send notifications to multiple channels/providers
        return CompletableFuture
                .allOf(futures.toArray(new CompletableFuture[0]))
                .thenApply(v ->
                        futures.stream()
                                .map(CompletableFuture::join)
                                .toList());
    }

    /**
     *
     *
     * @param notifications the list of notifications to send.
     *
     * @return @return a list of results.
     */
    public CompletableFuture<List<Result>> dispatchAll(
            final List<Notification> notifications) {

        // To get the channels to send the notifications.
        Set<Channel> channels =
                notifications.stream().map(Notification::channel).collect(Collectors.toSet());

        // To group the notification by channel.
        Map<Channel, List<Notification>> notificationsByChannel =
                notifications.stream().collect(Collectors.groupingBy(Notification::channel));

        // To send the notifications according to providers configured.
        List<List<CompletableFuture<Result>>> futures =
                providers.stream()
                        .filter(p -> channels.contains(p.getChannel()))
                        .map(p -> p.sendAsync(notificationsByChannel.get(p.getChannel())))
                        .toList();

        List<CompletableFuture<Result>> allFutures =
                futures.stream()
                        .flatMap(List::stream)
                        .toList();

        // To join all futures because the logic has could send notifications to multiple channels/providers
        return CompletableFuture
                .allOf()
                .thenApply(v ->
                        allFutures.stream()
                                .map(CompletableFuture::join)
                                .toList());

    }
}

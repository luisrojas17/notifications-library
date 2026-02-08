package com.acme.notifications.providers.impl;

import com.acme.notifications.domains.Channel;
import com.acme.notifications.domains.Notification;
import com.acme.notifications.helpers.ConfigurationHelper;
import com.acme.notifications.providers.AbstractNotificationProvider;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;

@Slf4j
public class FirebaseProvider extends AbstractNotificationProvider {

    private static final String PROJECT_ID_VARIABLE_NAME = "FIREBASE_PROJECT_ID";
    private static final String ENDPOINT_VARIABLE_NAME = "FIREBASE_ENDPOINT";

    private final DataConfiguration dataConfiguration;

    public FirebaseProvider(final ExecutorService executorService) {

        log.info("Initializing FirebaseProvider...");

        configurationRules.checkThreadPoolExecutor(executorService);

        super.executorService = executorService;

        // Load Firebase Cloud Messaging config from environment variables
        this.dataConfiguration =
                DataConfiguration.builder()
                        .projectId(ConfigurationHelper.getValue(PROJECT_ID_VARIABLE_NAME))
                        .endpoint(ConfigurationHelper.getValue(ENDPOINT_VARIABLE_NAME))
                        .build();

        log.info("FirebaseProvider initialized.");
    }

    @Override
    public Channel getChannel() {
        return Channel.PUSH;
    }

    public void sendSync(final Notification notification) throws Exception {

        // To implement provider's API
        log.info("Sending notification [{}] to [{}].", notification, dataConfiguration.getEndpoint());

        Thread.sleep(1000);

        log.info("Notification [{}] was sent to [{}].", notification, dataConfiguration.getEndpoint());
    }

    @Builder
    @Getter
    static class DataConfiguration {
        private String projectId;
        private String endpoint;

    }
}

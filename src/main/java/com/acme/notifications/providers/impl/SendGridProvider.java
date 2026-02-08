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
public class SendGridProvider extends AbstractNotificationProvider {

    private static final String API_KEY_VARIABLE_NAME = "SENDGRID_API_KEY";
    private static final String ENDPOINT_VARIABLE_NAME = "SENDGRID_ENDPOINT";
    private static final String SENDER_VARIABLE_NAME = "SENDGRID_SENDER";
    private static final String USER_VARIABLE_NAME = "SENDGRID_USER";
    private static final String PASS_VARIABLE_NAME = "SENDGRID_PASS";

    private final DataConfiguration dataConfiguration;

    public SendGridProvider(final ExecutorService executorService) {

        log.info("Initializing SendGridProvider...");

        configurationRules.checkThreadPoolExecutor(executorService);

        super.executorService = executorService;

        // Load SendGrid's config from environment variables
        this.dataConfiguration =
                DataConfiguration.builder()
                        .apiKey(ConfigurationHelper.getValue(API_KEY_VARIABLE_NAME))
                        .endpoint(ConfigurationHelper.getValue(ENDPOINT_VARIABLE_NAME))
                        .sender(ConfigurationHelper.getValue(SENDER_VARIABLE_NAME))
                        .user(ConfigurationHelper.getValue(USER_VARIABLE_NAME))
                        .pass(ConfigurationHelper.getValue(PASS_VARIABLE_NAME))
                        .build();

        log.info("SendGridProvider initialized.");
    }

    @Override
    public Channel getChannel() {
        return Channel.EMAIL;
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

        private String apiKey;
        private String endpoint;
        private String sender;
        private String user;
        private String pass;
    }
}

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
public class TwilioProvider extends AbstractNotificationProvider {

    private final static String ENDPOINT_VARIABLE_NAME = "TWILIO_ENDPOINT";
    private final static String SID_VARIABLE_NAME = "TWILIO_SID";
    private final static String AUTH_TOKEN_VARIABLE_NAME = "TWILIO_AUTH_TOKEN";

    private final DataConfiguration dataConfiguration;

    public TwilioProvider(final ExecutorService executorService) {

        log.info("Initializing TwilioProvider...");

        configurationRules.checkThreadPoolExecutor(executorService);

        super.executorService = executorService;

        // Load Twilio's config from environment variables
        this.dataConfiguration =
                DataConfiguration.builder()
                        .authToken(ConfigurationHelper.getValue(AUTH_TOKEN_VARIABLE_NAME))
                        .endpoint(ConfigurationHelper.getValue(ENDPOINT_VARIABLE_NAME))
                        .sid(ConfigurationHelper.getValue(SID_VARIABLE_NAME))
                        .build();

        log.info("TwilioProvider initialized.");
    }

    @Override
    public Channel getChannel() {
        return Channel.SMS;
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
        private String authToken;
        private String endpoint;
        private String sid;
    }
}

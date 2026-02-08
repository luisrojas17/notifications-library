package com.acme.notifications.rules;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.concurrent.ExecutorService;

/**
 * Class that contains the configuration rules for the notification providers.
 * This class implements the pattern Singleton.
 *
 * @author Jose Luis Rojas Gomez
 */
@Slf4j
public class ConfigurationRules {

    public static final ConfigurationRules INSTANCE = new ConfigurationRules();

    public static ConfigurationRules getInstance() {
        return INSTANCE;
    }

    /**
     * To check if the thread pool executor was initialized.
     * If the thread pool executor is null, it throws an exception.
     *
     * @param executorService the thread pool executor.
     */
    public void checkThreadPoolExecutor(final ExecutorService executorService) {
        if (Objects.isNull(executorService)) {
            String errorMessage = "Thread pool executor cannot be null.";

            log.error(errorMessage);

            throw new IllegalArgumentException(errorMessage);
        }
    }
}

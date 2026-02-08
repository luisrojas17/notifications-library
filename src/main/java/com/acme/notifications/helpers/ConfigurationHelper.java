package com.acme.notifications.helpers;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class ConfigurationHelper {

    public static String getValue(final String key) {

        if (key == null) {
            String errorMessage = "Environment key cannot be null.";
            log.error(errorMessage);

            throw new IllegalArgumentException("Environment key cannot be null.");
        }

        log.info("Getting environment variable [{}].", key);

        String value = System.getenv(key);

        if (Objects.isNull(value) || value.isEmpty()) {
            String errorMessage = String.format("Missing environment key [%s] ", key);
            log.error(errorMessage);

            throw new IllegalArgumentException(
                    errorMessage);
        }

        return value;
    }
}

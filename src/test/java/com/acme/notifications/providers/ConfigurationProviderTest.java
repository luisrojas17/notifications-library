package com.acme.notifications.providers;

import com.acme.notifications.providers.impl.SendGridProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ConfigurationProviderTest {

    @Test
    void testConfigurationFailure() {

        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    List<NotificationProvider> providers = List.of(
                            new SendGridProvider(null)
                    );
                }
        );

        Assertions.assertEquals("Thread pool executor cannot be null.", exception.getMessage());
    }

}

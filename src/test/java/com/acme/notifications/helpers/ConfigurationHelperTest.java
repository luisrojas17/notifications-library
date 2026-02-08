package com.acme.notifications.helpers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ConfigurationHelperTest {

    @Test
    void testGetValueSuccess() {

        Assertions.assertDoesNotThrow(() ->
                ConfigurationHelper.getValue("SENDGRID_API_KEY"));

    }

    @Test
    void testGetNullValueFailure() {

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> ConfigurationHelper.getValue(null)
        );
    }

    @Test
    void testGetMissingValueFailure() {

        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> ConfigurationHelper.getValue("SOME_KEY_VALUE")
        );
    }

}

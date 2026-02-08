package com.acme.notifications.domains;

/**
 * This class represents the result of a notification sent.
 *
 * @author Jose Luis Rojas Gomez
 */
public record Result (
        boolean success,
        String provider,
        String message)
{}

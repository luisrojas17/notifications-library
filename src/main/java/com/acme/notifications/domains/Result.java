package com.acme.notifications.domains;

public record Result (
        boolean success,
        String provider,
        String message)
{}

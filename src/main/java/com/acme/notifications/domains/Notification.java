package com.acme.notifications.domains;

public record Notification(
        Channel channel, String recipient, String title, String message)
{}

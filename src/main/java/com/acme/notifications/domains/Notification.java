package com.acme.notifications.domains;

/**
 * This class represents a notification.
 *
 * @author Jose Luis Rojas Gomez
 */
public record Notification(
        Channel channel, String recipient, String title, String message)
{}

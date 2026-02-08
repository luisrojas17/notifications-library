package com.acme.notifications.domains;

import lombok.Data;

import java.util.Map;

@Data
public class Message {
    private String recipient;
    private String subject;
    private String body;
    private String content;
    private Map<String, Object> metadata;
    private Channel channel;
}

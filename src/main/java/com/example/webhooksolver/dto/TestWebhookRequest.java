package com.example.webhooksolver.dto;

import lombok.Data;

@Data
public class TestWebhookRequest {
    private String webhookUrl;
    private String testPayload;
}

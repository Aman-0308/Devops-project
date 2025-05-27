package com.epharmacy.entitlement.model;

import lombok.Data;

@Data
public class EntitlementRequest {
    private String userId;
    private String sessionId;
    private String token;
    private String deviceId;
    private String feature;
}



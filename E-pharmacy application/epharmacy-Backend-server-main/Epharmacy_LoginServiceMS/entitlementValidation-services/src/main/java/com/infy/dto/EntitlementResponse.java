package com.epharmacy.entitlement.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntitlementResponse {
    private boolean entitled;
    private String reason;
}

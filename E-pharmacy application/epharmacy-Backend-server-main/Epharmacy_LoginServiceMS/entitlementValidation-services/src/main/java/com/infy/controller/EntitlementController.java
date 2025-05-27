@package com.epharmacy.entitlement.controller;

import com.epharmacy.entitlement.model.EntitlementRequest;
import com.epharmacy.entitlement.model.EntitlementResponse;
import com.epharmacy.entitlement.service.EntitlementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/entitlement")
@RequiredArgsConstructor
public class EntitlementController {

    private final EntitlementService entitlementService;

    @PostMapping("/check")
    public ResponseEntity<EntitlementResponse> checkUserEntitlement(@RequestBody EntitlementRequest request) {
        return ResponseEntity.ok(entitlementService.checkEntitlement(request));
    }
}

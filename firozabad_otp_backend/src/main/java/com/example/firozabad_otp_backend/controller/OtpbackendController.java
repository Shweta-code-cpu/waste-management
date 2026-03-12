package com.example.firozabad_otp_backend.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.firozabad_otp_backend.model.QrOtpData;
import com.example.firozabad_otp_backend.service.OtpBackendService;


@RestController
@RequestMapping("/api/qr")
public class OtpbackendController {

    private final OtpBackendService service;

    public OtpbackendController(OtpBackendService service) {
        this.service = service;
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyMobile(@RequestBody Map<String, String> request) {
        String lastFourDigits = request.get("lastFourDigits");

        if (lastFourDigits == null || lastFourDigits.length() != 4) {
            return ResponseEntity.badRequest().body("Invalid last 4 digits.");
        }

        Optional<QrOtpData> matchingData = service.getByLastFourDigits(lastFourDigits);

        if (matchingData.isPresent()) {
            String mobileNumber = matchingData.get().getMobile();

            // Ensure mobile number is formatted correctly
            if (!mobileNumber.startsWith("+91")) {
                mobileNumber = "+91" + mobileNumber;
            }

            return ResponseEntity.ok(Map.of("mobile", mobileNumber));
        } else {
            return ResponseEntity.status(404).body("No matching mobile number found.");
        }
    }
}

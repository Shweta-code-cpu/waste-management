package com.example.firozabad_otp_backend.service;


import org.springframework.stereotype.Service;

import com.example.firozabad_otp_backend.model.QrOtpData;
import com.example.firozabad_otp_backend.repository.OtpbackendRepo;

import java.util.Optional;


@Service
public class OtpBackendService {

    private final OtpbackendRepo repository;

    public OtpBackendService(OtpbackendRepo repository) {
        this.repository = repository;
    }

    public Optional<QrOtpData> getByLastFourDigits(String lastFourDigits) {
        return repository.findByMobileEndingWith(lastFourDigits);
    }
}
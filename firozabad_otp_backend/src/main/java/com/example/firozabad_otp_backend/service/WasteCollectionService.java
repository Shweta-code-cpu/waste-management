package com.example.firozabad_otp_backend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.firozabad_otp_backend.repository.WasteCollectionRepository;

@Service
public class WasteCollectionService {

    @Autowired
    private WasteCollectionRepository repository;

    public Long getCollectedHouseCount(Integer collectorId) {
        return repository.countCollectedByCollectorId(collectorId);
    }
}



package com.example.firozabad_otp_backend.controller;

import com.example.firozabad_otp_backend.model.WasteCollectorLoginData;
import com.example.firozabad_otp_backend.repository.WasteCollectorLoginDataRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.Random;

//@RestController
//@RequestMapping("/api/waste-collector")
//public class WasteCollectorLoginController {
//
//    @Autowired
//    private WasteCollectorLoginDataRepository wasteCollectorLoginDataRepository;
//
//    // POST API - Add or Validate Collector Data
//    @PostMapping("/add")
//    public ResponseEntity<?> addWasteCollector(@RequestBody WasteCollectorLoginData request) {
//        Optional<WasteCollectorLoginData> existingData = wasteCollectorLoginDataRepository.findByMobile(request.getMobile());
//
//        if (existingData.isPresent()) {
//            // Return JSON response instead of plain text
//            return ResponseEntity.ok(Map.of("message", "Mobile number already exists. No changes were made."));
//        }
//
//        // Generate a unique collector_id
//        int newCollectorId;
//        do {
//            newCollectorId = new Random().nextInt(99999);
//        } while (wasteCollectorLoginDataRepository.existsByCollectorId(newCollectorId));
//
//        request.setCollectorId(newCollectorId);
//        wasteCollectorLoginDataRepository.save(request);
//
//        // Return JSON response
//        return ResponseEntity.ok(Map.of("message", "New Waste Collector added successfully", "collector_id", newCollectorId));
//    }
//
//}

@RestController
@RequestMapping("/api/waste-collector")
public class WasteCollectorLoginController {

    @Autowired
    private WasteCollectorLoginDataRepository wasteCollectorLoginDataRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addWasteCollector(@RequestBody WasteCollectorLoginData request) {
        Optional<WasteCollectorLoginData> existingData = wasteCollectorLoginDataRepository.findByMobile(request.getMobile());

        if (existingData.isPresent()) {
            int existingCollectorId = existingData.get().getCollectorId();
            return ResponseEntity.ok(
                Map.of(
                    "message", "Mobile number already exists. No changes were made.",
                    "collector_id", existingCollectorId
                )
            );
        }

        int newCollectorId;
        do {
            newCollectorId = new Random().nextInt(99999);
        } while (wasteCollectorLoginDataRepository.existsByCollectorId(newCollectorId));

        request.setCollectorId(newCollectorId);
        wasteCollectorLoginDataRepository.save(request);

        return ResponseEntity.ok(
            Map.of(
                "message", "New Waste Collector added successfully",
                "collector_id", newCollectorId
            )
        );
    }
}



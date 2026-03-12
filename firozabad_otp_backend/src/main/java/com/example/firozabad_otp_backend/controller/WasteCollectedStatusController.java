

//package com.example.firozabad_otp_backend.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import com.example.firozabad_otp_backend.service.WasteCollectedStatusService;
//
//@RestController
//@RequestMapping("/waste")
//public class WasteCollectedStatusController {
//
//    @Autowired
//    private WasteCollectedStatusService service;
//
//    @PostMapping("/save")
//    public String saveWasteStatus(
//        @RequestParam String residentName,
//        @RequestParam String residentAddress,
//        @RequestParam String mobile,
//        @RequestParam String status,
//        @RequestParam(required = false) String wasteNotCollectedReason,
//        @RequestParam Boolean otpVerified,
//        @RequestParam(required = false) String otpFailureReason
//    ) {
//        service.saveWasteStatus(residentName, residentAddress, mobile, status, wasteNotCollectedReason, otpVerified, otpFailureReason);
//        return "Waste collection status saved successfully!";
//    }
//}

package com.example.firozabad_otp_backend.controller;

import com.example.firozabad_otp_backend.model.WasteCollectedStatusTable;
import com.example.firozabad_otp_backend.model.WasteCollectorLoginData;
import com.example.firozabad_otp_backend.repository.WasteCollectedStatusRepository;
import com.example.firozabad_otp_backend.repository.WasteCollectorLoginDataRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/waste-collection")
public class WasteCollectedStatusController {

    @Autowired
    private WasteCollectedStatusRepository wasteCollectedStatusRepository;
    
    @Autowired
    private WasteCollectorLoginDataRepository wasteCollectorLoginDataRepository;

    // POST API - Add Collection Status
//    @PostMapping("/add-status")
//    public ResponseEntity<?> addWasteCollectionStatus(@RequestBody WasteCollectedStatusTable request) {
//        request.setTimestamp(LocalDateTime.now());
//        WasteCollectedStatusTable savedEntry = wasteCollectedStatusRepository.save(request);
//        return ResponseEntity.ok(savedEntry);
//    }
    
    @PostMapping("/add-status")
    public ResponseEntity<?> addWasteCollectionStatus(@RequestBody WasteCollectedStatusTable request) {
        if (request.getCollector_id() == null) {
            return ResponseEntity.badRequest().body("Collector ID is required.");
        }

        request.setTimestamp(LocalDateTime.now());
        WasteCollectedStatusTable savedEntry = wasteCollectedStatusRepository.save(request);

        return ResponseEntity.ok(savedEntry);
    }


    
    // GET API - Filter by Status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<WasteCollectedStatusTable>> getStatusByType(@PathVariable String status) {
        List<WasteCollectedStatusTable> result = wasteCollectedStatusRepository.findByStatus(status);
        return ResponseEntity.ok(result);
    }

    // GET API - All Records
    @GetMapping("/all")
    public ResponseEntity<List<WasteCollectedStatusTable>> getAllWasteCollectionStatus() {
        return ResponseEntity.ok(wasteCollectedStatusRepository.findAll());
    }
}

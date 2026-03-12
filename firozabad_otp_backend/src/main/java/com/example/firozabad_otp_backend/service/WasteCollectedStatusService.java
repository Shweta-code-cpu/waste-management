//
//package com.example.firozabad_otp_backend.service;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.example.firozabad_otp_backend.model.WasteCollectedStatusTable;
//import com.example.firozabad_otp_backend.repository.WasteCollectedStatusRepository;
//
//@Service
//public class WasteCollectedStatusService {
//
//    @Autowired
//    private WasteCollectedStatusRepository repository;
//
//    public void saveWasteStatus(String residentName, String residentAddress, String mobile, String status, 
//                                String wasteNotCollectedReason, Boolean otpVerified, String otpFailureReason) {
//        
//        WasteCollectedStatusTable record = new WasteCollectedStatusTable();
//        record.setResidentName(residentName);
//        record.setResidentAddress(residentAddress);
//        record.setMobile(mobile);
//        record.setStatus(status);
//        
//        // If waste was NOT collected, store the reason
//        record.setWasteNotCollectedReason(status.equalsIgnoreCase("Not Collected") ? wasteNotCollectedReason : null);
//        
//        // Set OTP verification status
//        record.setOtpVerified(otpVerified);
//        record.setOtpFailureReason(!otpVerified ? otpFailureReason : null);
//        
//        repository.save(record);
//    }
//}

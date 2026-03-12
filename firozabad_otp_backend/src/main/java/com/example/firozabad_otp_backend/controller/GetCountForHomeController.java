package com.example.firozabad_otp_backend.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.firozabad_otp_backend.service.WasteCollectionService;

@RestController
@RequestMapping("/api/waste-collection")
public class GetCountForHomeController {
	
	   @Autowired
	    private WasteCollectionService service;

	    @GetMapping("/count-collected")
	    public Long getCollectedCount(@RequestParam Integer collectorId) {
	        return service.getCollectedHouseCount(collectorId);
	    }
	

}

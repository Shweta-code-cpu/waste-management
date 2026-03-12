package com.example.firozabad_otp_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@RestController
@RequestMapping("/Ferozabad")

public class PortalFetchController {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private static final Logger logger = LoggerFactory.getLogger(PortalFetchController.class);
	@GetMapping("/checkapi")
	public String checkapi() {
		return "War Working !";
	}
	
	@CrossOrigin(origins = "*")
	@PostMapping("/getZ1Type")
	public Map<String, Object> getZ1Type(String type) {
	    Map<String, Object> map = new HashMap<>();
	    try {
	        List<Map<String, Object>> survey_num_list = jdbcTemplate.queryForList(
	        		"SELECT collection_id, house_id, resident_name, resident_address, status,  updated_at, mobile, reason FROM waste_log_table ORDER BY updated_at DESC "
	        );
	        if (survey_num_list == null || survey_num_list.isEmpty()) {
	            map.put("status", true);
	            map.put("message", "Data not available!");
	        } else {
	            map.put("status", true);
	            map.put("message", "Data found!");
	        }
	        map.put("data", survey_num_list);
	        map.put("total_records", survey_num_list.size());
	    } catch (Exception e) {
	        map.put("status", false);
	        map.put("message", e.getLocalizedMessage());
	    }
	    return map;
	}

}



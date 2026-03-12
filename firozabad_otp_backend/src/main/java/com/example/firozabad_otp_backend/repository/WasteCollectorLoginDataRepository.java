


package com.example.firozabad_otp_backend.repository;

import com.example.firozabad_otp_backend.model.WasteCollectorLoginData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WasteCollectorLoginDataRepository extends JpaRepository<WasteCollectorLoginData, Integer> {
    
    // Check if mobile exists
    Optional<WasteCollectorLoginData> findByMobile(String mobile);

    // Check if collector_id already exists
    boolean existsByCollectorId(Integer collectorId);
    
    Optional<WasteCollectorLoginData> findByCollectorId(Integer collectorId);

}

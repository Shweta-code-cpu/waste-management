package com.example.firozabad_otp_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.firozabad_otp_backend.model.WasteCollectedStatusTable;

public interface WasteCollectionRepository extends JpaRepository<WasteCollectedStatusTable, Long> {

    @Query("SELECT COUNT(w) FROM WasteCollectedStatusTable w WHERE w.collector_id = :collectorId AND w.status = 'Collected'")
    Long countCollectedByCollectorId(@Param("collectorId") Integer collectorId);
}



//package com.example.firozabad_otp_backend.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//import com.example.firozabad_otp_backend.model.WasteCollectedStatusTable;
//
//@Repository
//public interface WasteCollectedStatusRepository extends JpaRepository<WasteCollectedStatusTable, Long> {
//}

package com.example.firozabad_otp_backend.repository;

import com.example.firozabad_otp_backend.model.WasteCollectedStatusTable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WasteCollectedStatusRepository extends JpaRepository<WasteCollectedStatusTable, Long> {
    List<WasteCollectedStatusTable> findByStatus(String status);
}

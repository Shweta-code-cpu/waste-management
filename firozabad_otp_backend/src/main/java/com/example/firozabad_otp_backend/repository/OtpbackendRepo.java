package com.example.firozabad_otp_backend.repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.example.firozabad_otp_backend.model.QrOtpData;

@Repository
public interface OtpbackendRepo extends JpaRepository<QrOtpData, String> {

	@Query(value = "SELECT * FROM jagdamba_nagar WHERE CAST(mobile AS TEXT) LIKE '%' || :lastFourDigits LIMIT 1", nativeQuery = true)
	Optional<QrOtpData> findByMobileEndingWith(@Param("lastFourDigits") String lastFourDigits);

}

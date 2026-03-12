package com.example.firozabad_otp_backend.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.*;
import org.locationtech.jts.geom.Geometry; // For GEOMETRY data type

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "waste_log_table")
public class WasteCollectedStatusTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collection_id", nullable = false)
    private Long collection_id;  // SERIAL (PK)

    
    @Column(name = "house_id", nullable = false)
    private String house_id;  // INT (FK)

    
//    @ManyToOne
//    @JoinColumn(name = "collector_id", referencedColumnName = "collector_id", nullable = false)
//    private WasteCollectorLoginData collector;
    
    @Column(name = "collector_id", nullable = false)
    private Integer collector_id;  

    @Column(name = "resident_name", nullable = false)
    private String residentName;  

    @Column(name = "resident_address", nullable = false)
    private String residentAddress;

    @Column(name = "mobile", nullable = false)
    private String mobile;

    @Column(name = "status", nullable = false)
    private String status;  // e.g., "Collected", "Not Collected"

    @Column(name = "reason", nullable = true, length = 255)
    private String reason;  // Reason for "Not Collected" cases

    @Column(name = "otp_verified", nullable = false)
    private Boolean otpVerified; // True if Firebase OTP was verified

    @Column(name = "otp_failure_reason", nullable = true, length = 255)
    private String otpFailureReason; // "OTP Expired", "Wrong OTP", "Network Issue"

    @Column(name = "gps_location", nullable = true, columnDefinition = "geometry")
    private Geometry gpsLocation; // GEOMETRY data type

    @Column(name = "scanned_phone_no", nullable = true)
    private String scannedPhoneNo;  // VARCHAR for scanned phone number



	public Long getCollectionId() {
		return collection_id;
	}

	public void setCollectionId(Long collection_id) {
		this.collection_id = collection_id;
	}

	public String getHouseId() {
		return house_id;
	}

	public void setHouseId(String house_id) {
		this.house_id = house_id;
	}
	public String getResidentName() {
		return residentName;
	}

	public void setResidentName(String residentName) {
		this.residentName = residentName;
	}

	public String getResidentAddress() {
		return residentAddress;
	}

	public void setResidentAddress(String residentAddress) {
		this.residentAddress = residentAddress;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Boolean getOtpVerified() {
		return otpVerified;
	}

	public void setOtpVerified(Boolean otpVerified) {
		this.otpVerified = otpVerified;
	}

	public String getOtpFailureReason() {
		return otpFailureReason;
	}

	public void setOtpFailureReason(String otpFailureReason) {
		this.otpFailureReason = otpFailureReason;
	}

	public Geometry getGpsLocation() {
		return gpsLocation;
	}

	public void setGpsLocation(Geometry gpsLocation) {
		this.gpsLocation = gpsLocation;
	}

	public String getScannedPhoneNo() {
		return scannedPhoneNo;
	}

	public void setScannedPhoneNo(String scannedPhoneNo) {
		this.scannedPhoneNo = scannedPhoneNo;
	}

	public Integer getOtpRetryCount() {
		return otpRetryCount;
	}

	public void setOtpRetryCount(Integer otpRetryCount) {
		this.otpRetryCount = otpRetryCount;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public Long getCollection_id() {
		return collection_id;
	}

	public void setCollection_id(Long collection_id) {
		this.collection_id = collection_id;
	}

	public String getHouse_id() {
		return house_id;
	}

	public void setHouse_id(String house_id) {
		this.house_id = house_id;
	}

	public Integer getCollector_id() {
		return collector_id;
	}

	public void setCollector_id(Integer collector_id) {
		this.collector_id = collector_id;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	@Column(name = "otp_retry_count", nullable = false)
    private Integer otpRetryCount; // INT for OTP retry count

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp; // TIMESTAMP data type
}


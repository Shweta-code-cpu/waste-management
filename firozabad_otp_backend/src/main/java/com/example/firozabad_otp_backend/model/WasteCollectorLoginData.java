
package com.example.firozabad_otp_backend.model;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "wastecollector_login_data")
public class WasteCollectorLoginData {

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getWastecollectorImage() {
		return wastecollectorImage;
	}

	public void setWastecollectorImage(String wastecollectorImage) {
		this.wastecollectorImage = wastecollectorImage;
	}

    @Column(nullable = false, unique = true)
    private String mobile;  // ✅ Unique mobile number

    @Column
    private String userName;

    @Column(columnDefinition = "TEXT")
    private String wastecollectorImage;
    
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "collector_id", nullable = false)
    private Integer collectorId;

	public Integer getCollectorId() {
		return collectorId;
	}

	public void setCollectorId(Integer collectorId) {
		this.collectorId = collectorId;
	}
}

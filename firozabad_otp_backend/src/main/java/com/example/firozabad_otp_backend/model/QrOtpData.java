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
@Table(name = "jagdamba_nagar")
public class QrOtpData {

    @Id  // ✅ Use `mobile` as primary key
    @Column(nullable = false, unique = true)
    private String mobile;  // Full mobile number stored

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}


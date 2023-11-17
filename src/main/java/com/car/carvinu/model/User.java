package com.car.carvinu.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Setter
@Getter
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "status")
    private String status;

    @Column(name = "otp_token")
    private int otpToken;

    @Column(name = "mobile_number", nullable = false)
    private Long mobileNumber;

    @Column(name = "creation_date", nullable = false)
    private Date creationDate;

    @Column(name = "modified_date")
    private Date modifiedDate;

    @Column(name = "last_login_date")
    private Date lastLoginDate;

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }


}

package com.car.carvinu.service;

import org.springframework.stereotype.Service;

@Service
public class OtpServiceImpl implements OtpService {

    @Override
    public int generateOtp() {
        return (int) (Math.random() * 9000) + 1000;
    }

    @Override
    public void sendOTP(Long mobileNumber, String otpToken) {

    }
}

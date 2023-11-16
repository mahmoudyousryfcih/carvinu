package com.car.carvinu.service;

public interface OtpService {

    int generateOtp();

    void sendOTP(Long mobileNumber, String otpToken);
}

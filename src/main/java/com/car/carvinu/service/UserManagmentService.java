package com.car.carvinu.service;

import com.car.carvinu.core.model.OTPRequestDTO;
import com.car.carvinu.core.model.UserRequestDTO;
import com.car.carvinu.core.model.UserResponseDTO;
import com.car.carvinu.exception.UserManagmentException;

public interface UserManagmentService {

    UserResponseDTO registerUser(UserRequestDTO userRequestDTO) throws UserManagmentException;

    UserResponseDTO verifyOTP(OTPRequestDTO otPRequestDTO) throws UserManagmentException;

}

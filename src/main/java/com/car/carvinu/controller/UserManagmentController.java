package com.car.carvinu.controller;


import com.car.carvinu.core.UserManagmentApi;
import com.car.carvinu.core.model.OTPRequestDTO;
import com.car.carvinu.core.model.UserRequestDTO;
import com.car.carvinu.core.model.UserResponseDTO;
import com.car.carvinu.exception.UserManagmentException;
import com.car.carvinu.service.UserManagmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class UserManagmentController implements UserManagmentApi {

    @Autowired
    private UserManagmentService userManagmentService;


    @Override
    public ResponseEntity<UserResponseDTO> loginUser(UserRequestDTO userRequestDTO) {
        return null;
    }

    @Override
    public ResponseEntity<UserResponseDTO> registerUser(UserRequestDTO userRequestDTO) {
        try {
            return ResponseEntity.ok(userManagmentService.registerUser(userRequestDTO));
        } catch (UserManagmentException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ResponseEntity<UserResponseDTO> editUserProfile(UserRequestDTO userRequestDTO) {
        return null;
    }

    @Override
    public ResponseEntity<UserResponseDTO> verifyOTP(OTPRequestDTO otPRequestDTO) {
        try {
            return ResponseEntity.ok(userManagmentService.verifyOTP(otPRequestDTO));
        } catch (UserManagmentException e) {
            throw new RuntimeException(e);
        }
    }

}

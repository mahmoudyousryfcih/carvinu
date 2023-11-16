package com.car.carvinu.service;

import com.car.carvinu.constant.Constant;
import com.car.carvinu.core.model.OTPRequestDTO;
import com.car.carvinu.core.model.UserRequestDTO;
import com.car.carvinu.core.model.UserResponseDTO;
import com.car.carvinu.exception.UserManagmentException;
import com.car.carvinu.mapper.UserManagmentMapping;
import com.car.carvinu.model.User;
import com.car.carvinu.repository.UserManagmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Slf4j
@Service
public class UserManagmentServiceImpl implements UserManagmentService {

    @Autowired
    private UserManagmentRepository userManagmentRepository;
    @Autowired
    private UserManagmentMapping userManagmentMapping;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OtpService otpService;

    @Override
    public UserResponseDTO registerUser(UserRequestDTO userRequestDTO) throws UserManagmentException {
        log.info("---------------<< UserManagmentService :: registerUser :  " + userRequestDTO.toString() + " >>---------------");
        boolean userExist = isUserExist(userRequestDTO.getMobileNumber());
        if (userExist) {
            log.warn("User with the provided mobile number: {} already exists", userRequestDTO.getMobileNumber());
            throw new UserManagmentException("User with the provided mobile number already exists");
        }
        boolean userValid = isUserValid(userRequestDTO);
        if (userValid) {
            int otpCode = otpService.generateOtp();
            otpService.sendOTP(Long.parseLong(userRequestDTO.getMobileNumber()), String.valueOf(otpCode));
            User user = userManagmentMapping.toUser(userRequestDTO);
            user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
            user.setStatus("INACTIVE");
            user.setCreationDate(new Date());
            user.setOtpToken(otpCode);
            userManagmentRepository.save(user);
            return userManagmentMapping.toUserResponseDto(user);
        } else {
            log.warn("invalid email format: {} or mobile number: {}", userRequestDTO.getEmail(), userRequestDTO.getMobileNumber());
            throw new UserManagmentException("invalid email format or mobile number");
        }

    }

    @Override
    public UserResponseDTO verifyOTP(OTPRequestDTO otPRequestDTO) throws UserManagmentException {
        log.info("---------------<< UserManagmentService :: verifyOTP   " + " >>---------------");
        Optional<User> user = userManagmentRepository.findUserByMobileNumber(otPRequestDTO.getMobileNumber());
        if (user.isPresent()) {
            User userInfo = user.get();
            if (otPRequestDTO.getOtpToken().equals(String.valueOf(userInfo.getOtpToken()))) {
                userInfo.setStatus("ACTIVE");
                userManagmentRepository.save(userInfo);
                return userManagmentMapping.toUserResponseDto(userInfo);
            } else {
                throw new UserManagmentException("invalid OTP Token");
            }
        }
        return null;
    }

    private boolean isUserExist(String mobileNumber) {
        Optional<User> user = userManagmentRepository.findUserByMobileNumber(mobileNumber);
        if (user.isPresent())
            return true;
        return false;
    }

    private boolean isUserValid(UserRequestDTO userRequestDTO) {
        if (userRequestDTO.getMobileNumber().matches(Constant.REGEX_MOBILE_NUMBER) && userRequestDTO.getEmail().matches(Constant.REGEX_EMAIL))
            return true;
        return false;
    }
}

package com.car.carvinu.mapper;

import com.car.carvinu.core.model.UserRequestDTO;
import com.car.carvinu.core.model.UserResponseDTO;
import com.car.carvinu.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserManagmentMapping {

    User toUser(UserRequestDTO userRequestDTO);

    UserRequestDTO toUserRequestDto(User user);

    UserResponseDTO toUserResponseDto(User user);
}

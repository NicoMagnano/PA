package com.example.pa.controller.Mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import com.example.pa.User.User;
import com.example.pa.controller.DTO.UserDTO.UserProfileDTO;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserProfileDTO toUserProfileDTO(User user);

    @Mapping(target = "password", ignore = true) // Ignora campos que no deben actualizarse
    User toUser(UserProfileDTO userProfileDTO, @MappingTarget User user);
}


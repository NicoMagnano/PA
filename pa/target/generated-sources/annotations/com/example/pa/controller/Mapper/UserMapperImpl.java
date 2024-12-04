package com.example.pa.controller.Mapper;

import com.example.pa.User.User;
import com.example.pa.controller.DTO.UserDTO.UserProfileDTO;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-04T03:03:51-0300",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.40.0.z20241112-1021, environment: Java 17.0.13 (Eclipse Adoptium)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserProfileDTO toUserProfileDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserProfileDTO userProfileDTO = new UserProfileDTO();

        userProfileDTO.setDireccionEnvio( user.getDireccionEnvio() );
        userProfileDTO.setEmail( user.getEmail() );
        userProfileDTO.setFirstname( user.getFirstname() );
        userProfileDTO.setLastname( user.getLastname() );
        userProfileDTO.setTelefono( user.getTelefono() );

        return userProfileDTO;
    }

    @Override
    public User toUser(UserProfileDTO userProfileDTO, User user) {
        if ( userProfileDTO == null ) {
            return user;
        }

        user.setDireccionEnvio( userProfileDTO.getDireccionEnvio() );
        user.setEmail( userProfileDTO.getEmail() );
        user.setFirstname( userProfileDTO.getFirstname() );
        user.setLastname( userProfileDTO.getLastname() );
        user.setTelefono( userProfileDTO.getTelefono() );

        return user;
    }
}

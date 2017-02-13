package com.coolnimesh43.persistence.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.coolnimesh43.persistence.entity.User;
import com.coolnimesh43.persistence.rest.dto.UserDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userToUserDTO(User user);

    List<UserDTO> usersToUserDTOs(List<User> users);

    User userDTOToUser(UserDTO userDTO);

    List<User> userDTOsToUsers(List<UserDTO> userDTOs);

    default User map(Long id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }
}

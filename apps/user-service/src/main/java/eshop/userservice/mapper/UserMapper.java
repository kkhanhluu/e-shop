package eshop.userservice.mapper;

import eshop.userservice.dto.UserDTO;
import eshop.userservice.entities.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);
}

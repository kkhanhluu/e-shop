package eshop.userservice.mapper;

import eshop.userservice.dto.UserDTO;
import eshop.userservice.model.User;
import org.mapstruct.Mapper;

@Mapper(uses = {ObjectIdMapper.class})
public interface UserMapper {
    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);
}

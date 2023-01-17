package eshop.userservice.service;

import eshop.userservice.dto.UserDTO;
import eshop.userservice.mapper.UserMapper;
import eshop.userservice.mapper.UserMapperImpl;
import eshop.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Optional<UserDTO> getUserById(String id) {
     return  userRepository.findById(new ObjectId(id)).map(user -> userMapper.userToUserDTO(user));
    }
}

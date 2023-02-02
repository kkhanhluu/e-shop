package eshop.userservice.service;

import eshop.userservice.dto.UserDTO;
import eshop.userservice.mapper.UserMapper;
import eshop.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Optional<UserDTO> getUserById(UUID id) {
     return  userRepository.findById(id).map(user -> userMapper.userToUserDTO(user));
    }
}

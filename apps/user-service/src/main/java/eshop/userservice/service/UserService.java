package eshop.userservice.service;

import eshop.userservice.dto.UserDTO;
import eshop.userservice.entities.User;
import eshop.userservice.mapper.UserMapper;
import eshop.userservice.model.RegisterRequest;
import eshop.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public Optional<UserDTO> getUserById(UUID id) {
     return  userRepository.findById(id).map(user -> userMapper.userToUserDTO(user));
    }

    public void createUser(RegisterRequest request) {
        User user = User.builder().email(request.getEmail()).username(request.getUsername()).password(
                passwordEncoder.encode(request.getPassword())).build();
        userRepository.save(user);
    }
}

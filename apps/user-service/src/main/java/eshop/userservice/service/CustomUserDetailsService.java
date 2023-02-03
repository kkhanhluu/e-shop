package eshop.userservice.service;

import eshop.userservice.entities.User;
import eshop.userservice.model.SecurityUser;
import eshop.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService  {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByEmail(username);
        return optionalUser.map(user -> new SecurityUser(user))
                .orElseThrow(() -> new UsernameNotFoundException("Can not find user: " + username));
    }
}

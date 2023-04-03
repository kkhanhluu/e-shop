package eshop.userservice.service;

import eshop.api.exceptions.NotFoundException;
import eshop.userservice.entities.User;
import eshop.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OidcUserInfoService {
    private final UserRepository userRepository;

    public OidcUserInfo loadUser(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(NotFoundException::new);
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId().toString());
        claims.put("name", user.getUsername());
        claims.put("email", user.getEmail());
        claims.put("role", user.getRole().toString());
        return new OidcUserInfo(claims);
    }
}
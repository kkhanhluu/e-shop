package eshop.userservice.controller;

import eshop.api.exceptions.NotFoundException;
import eshop.userservice.dto.UserDTO;
import eshop.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/{userId}", produces = "application/json")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("userId")String userId) {
        return userService.getUserById(UUID.fromString(userId))
                .map(userDTO -> ResponseEntity.ok(userDTO))
                .orElseThrow(() -> new NotFoundException("No found user for userId: " + userId));
    }
}

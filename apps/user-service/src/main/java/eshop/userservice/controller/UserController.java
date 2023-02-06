package eshop.userservice.controller;

import eshop.api.exceptions.NotFoundException;
import eshop.userservice.dto.UserDTO;
import eshop.userservice.model.RegisterRequest;
import eshop.userservice.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @PostMapping(value = "/register")
    public void registerUser(@RequestBody RegisterRequest request, HttpServletResponse response) throws IOException {
        userService.createUser(request);
        response.sendRedirect("/login");
    }
}

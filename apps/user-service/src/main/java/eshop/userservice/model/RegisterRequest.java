package eshop.userservice.model;

import eshop.userservice.entities.UserRole;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    @NotNull(message = "Please provide an username")
    private String username;
    @NotNull(message = "Please provide an email")
    private String email;
    @NotNull(message = "Please provide a password")
    private String password;
    @NotNull(message = "Please provide a role")
    private UserRole role;
}
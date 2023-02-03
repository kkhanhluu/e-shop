package eshop.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.oauth2ResourceServer(j -> j.jwt().jwkSetUri("http://localhost:8080/oauth2/jwks"))
                .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated()).build();
    }
}

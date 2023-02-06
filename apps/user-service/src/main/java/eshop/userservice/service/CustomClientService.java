package eshop.userservice.service;

import eshop.userservice.entities.Client;
import eshop.userservice.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomClientService implements RegisteredClientRepository {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void save(RegisteredClient registeredClient) {
        Client client = Client.builder()
                .authMethod(registeredClient.getClientAuthenticationMethods().stream().findAny().get().getValue())
                .clientId(registeredClient.getClientId())
                .scopes(registeredClient.getScopes().stream().toList())
                .grantTypes(registeredClient.getAuthorizationGrantTypes().stream().map(type -> type.getValue()).collect(
                        Collectors.toList()))
                .redirectUri(registeredClient.getRedirectUris().stream().findAny().get())
                .secret(registeredClient.getClientSecret()).build();
        clientRepository.save(client);
    }

    @Override
    public RegisteredClient findById(String id) {
        Optional<Client> clientOptional = clientRepository.findById(UUID.fromString(id));
        return clientOptional.map(client -> fromClientToRegisteredClient(client)).orElseThrow();
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        Optional<Client> clientOptional = clientRepository.findByClientId(clientId);
        return clientOptional.map(client -> fromClientToRegisteredClient(client)).orElseThrow();
    }

    private RegisteredClient fromClientToRegisteredClient(Client client) {
        RegisteredClient.Builder builder = RegisteredClient.withId(String.valueOf(client.getId()))
                .clientId(client.getClientId())
                .clientSecret(client.getSecret())
                .redirectUri(client.getRedirectUri())
                .clientAuthenticationMethod(new ClientAuthenticationMethod(client.getAuthMethod()))
                .tokenSettings(TokenSettings.builder().accessTokenTimeToLive(Duration.ofHours(24)).build());
        client.getGrantTypes().stream().forEach(type -> {
            builder.authorizationGrantType(new AuthorizationGrantType(type));
        });
        client.getScopes().stream().forEach(scope -> {
            builder.scope(scope);
        });
        return builder.build();
    }

}

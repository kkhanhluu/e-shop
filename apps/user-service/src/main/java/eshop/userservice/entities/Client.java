package eshop.userservice.entities;

import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "clients")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String clientId;
    private String secret;
    private String redirectUri;
    @Type(ListArrayType.class)
    @Column(columnDefinition = "text[]")
    private List<String> scopes;
    private String authMethod;
    @Type(ListArrayType.class)
    @Column(columnDefinition = "text[]")
    private List<String> grantTypes;
}

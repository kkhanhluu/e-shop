package eshop.userservice.model;

import jakarta.validation.constraints.Email;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collation = "users")
public class User {
  @Id
  private ObjectId id;

  @CreatedDate
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime modifiedAt;

  @Indexed(unique = true)
  private String username;

  @Indexed(unique = true)
  @Email
  private String email;

  private String password;
}

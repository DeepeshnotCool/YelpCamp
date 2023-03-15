package me.deepesh.springyelpcamp.model.document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Document("users")
@NoArgsConstructor
public class User {

  @Id private String id;

  private String username;

  private String password;

  private String email;

  private String phone;

  private String fullName;

  private String image;

  private Set<Role> roles = new HashSet<>();

  public User(final String username, final String email, final String password) {

    this.username = username;
    this.email = email;
    this.password = password;
  }

  @Override
  public String toString() {

    return "User{" + "fullName='" + fullName + '\'' + '}';
  }
}

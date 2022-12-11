package me.jysh.springyelpcamp.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPayload {

  private String id;

  private String username;

  @JsonIgnore private String password;

  private String email;

  private String phone;

  private String fullName;

  private String image;

  @Builder.Default private Set<String> roles = new HashSet<>();
}

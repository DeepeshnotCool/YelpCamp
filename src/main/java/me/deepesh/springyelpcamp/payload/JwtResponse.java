package me.deepesh.springyelpcamp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {

  private String jwtToken;

  private String id;

  private String username;

  private String email;

  private List<String> roles;
}

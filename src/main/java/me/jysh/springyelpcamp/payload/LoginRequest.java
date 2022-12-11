package me.jysh.springyelpcamp.payload;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

  private String username;

  private String password;
}

package me.jysh.springyelpcamp.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

  @NotNull(message = "Username cannot be empty")
  private String username;

  @NotNull @Email private String email;

  @NotNull
  @Size(min = 6, max = 15)
  private String password;
}

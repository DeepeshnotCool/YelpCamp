package me.jysh.springyelpcamp.controller;

import lombok.RequiredArgsConstructor;
import me.jysh.springyelpcamp.enums.RoleEnum;
import me.jysh.springyelpcamp.exception.RequestValidationException;
import me.jysh.springyelpcamp.model.UserDetailsImpl;
import me.jysh.springyelpcamp.model.document.Role;
import me.jysh.springyelpcamp.model.document.User;
import me.jysh.springyelpcamp.payload.JwtResponse;
import me.jysh.springyelpcamp.payload.LoginRequest;
import me.jysh.springyelpcamp.payload.MessageResponse;
import me.jysh.springyelpcamp.payload.SignUpRequest;
import me.jysh.springyelpcamp.repository.RoleRepository;
import me.jysh.springyelpcamp.repository.UserRepository;
import me.jysh.springyelpcamp.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AuthController {

  private final AuthenticationManager authenticationManager;

  private final JwtService jwtService;

  private final UserRepository userRepository;

  private final RoleRepository roleRepository;

  private final PasswordEncoder passwordEncoder;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

    final Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);

    final String jwtToken = jwtService.generateJwtToken(authentication);

    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

    List<String> roles =
        userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());

    return ResponseEntity.ok()
        .body(
            new JwtResponse(
                jwtToken,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@RequestBody @Valid SignUpRequest signUpRequest)
      throws RequestValidationException {

    validateSignUpRequest(signUpRequest);

    final User user =
        new User(
            signUpRequest.getUsername(),
            signUpRequest.getEmail(),
            passwordEncoder.encode(signUpRequest.getPassword()));

    final Role role =
        roleRepository
            .findByName(RoleEnum.ROLE_USER)
            .orElseThrow(() -> new RuntimeException("Error: Role is not found"));

    user.getRoles().add(role);

    userRepository.save(user);

    return ResponseEntity.ok().body(new MessageResponse("User registered successfully"));
  }

  private void validateSignUpRequest(SignUpRequest signUpRequest)
      throws RequestValidationException {

    if (userRepository.existsByUsername(signUpRequest.getUsername())) {

      throw new RequestValidationException("Username is already taken");
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {

      throw new RequestValidationException("Email is already taken");
    }
  }
}

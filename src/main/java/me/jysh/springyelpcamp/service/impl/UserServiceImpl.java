package me.jysh.springyelpcamp.service.impl;

import lombok.RequiredArgsConstructor;
import me.jysh.springyelpcamp.exception.NotFoundException;
import me.jysh.springyelpcamp.model.UserDetailsImpl;
import me.jysh.springyelpcamp.model.document.User;
import me.jysh.springyelpcamp.repository.UserRepository;
import me.jysh.springyelpcamp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserServiceImpl implements UserService {

  final UserRepository userRepository;

  @Override
  public boolean existsByUsername(String username) {

    return false;
  }

  @Override
  public boolean existsByEmail(String email) {

    return false;
  }

  @Override
  public User getUserByUsername(final String username) {

    return userRepository
        .findByUsername(username)
        .orElseThrow(() -> new NotFoundException("User not found."));
  }

  @Override
  public User getLoggedInUser() {

    final UserDetailsImpl loggedInUserDetails =
        (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    return getUserByUsername(loggedInUserDetails.getUsername());
  }
}

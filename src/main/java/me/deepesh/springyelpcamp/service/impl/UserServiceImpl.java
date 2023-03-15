package me.deepesh.springyelpcamp.service.impl;

import lombok.RequiredArgsConstructor;
import me.deepesh.springyelpcamp.exception.NotFoundException;
import me.deepesh.springyelpcamp.model.UserDetailsImpl;
import me.deepesh.springyelpcamp.repository.UserRepository;
import me.deepesh.springyelpcamp.model.document.User;
import me.deepesh.springyelpcamp.service.UserService;
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

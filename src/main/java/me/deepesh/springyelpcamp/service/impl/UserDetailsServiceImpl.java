package me.deepesh.springyelpcamp.service.impl;

import lombok.RequiredArgsConstructor;
import me.deepesh.springyelpcamp.model.UserDetailsImpl;
import me.deepesh.springyelpcamp.repository.UserRepository;
import me.deepesh.springyelpcamp.model.document.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    User user =
        userRepository
            .findByUsername(username)
            .orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with username :: " + username));

    return UserDetailsImpl.build(user);
  }
}

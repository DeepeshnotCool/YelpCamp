package me.deepesh.springyelpcamp.service;

import me.deepesh.springyelpcamp.model.document.User;

public interface UserService {

  boolean existsByUsername(String username);

  boolean existsByEmail(String email);

  User getUserByUsername(String username);

  User getLoggedInUser();

}

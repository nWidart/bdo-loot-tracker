package com.bdoloottracker.user.service;

import com.bdoloottracker.user.entity.User;
import com.bdoloottracker.user.exception.ResourceAlreadyInUseException;
import com.bdoloottracker.user.request.RegisterUserRequest;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthService {

  private final UserService userService;

  public AuthService(UserService userService) {
    this.userService = userService;
  }

  public Optional<User> registerUser(RegisterUserRequest request) {
    String email = request.getEmail();
    if (emailAlreadyExists(email)) {
      log.error("Email already exists: " + email);
      throw new ResourceAlreadyInUseException("Email", "address", email);
    }
    User user = this.userService.createUser(request);
    User registeredNewUser = this.userService.save(user);

    return Optional.ofNullable(registeredNewUser);
  }

  public boolean emailAlreadyExists(String email) {
    return userService.existsByEmail(email);
  }
}

package com.bdoloottracker.user.service;


import com.bdoloottracker.user.entity.User;
import com.bdoloottracker.user.repository.UserRepository;
import com.bdoloottracker.user.request.RegisterUserRequest;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public Optional<User> findByName(String name) {
    Objects.requireNonNull(name, "name must be not null");
    return userRepository.findFirstByName(name);
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }

  public Optional<User> findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public Optional<User> findById(Long userId) {
    return userRepository.findById(userId);
  }

  public void register(RegisterUserRequest request) {
    String encodedPassword = this.passwordEncoder.encode(request.getPassword());
    User user = User.builder()
        .email(request.getEmail())
        .name(request.getName())
        .password(encodedPassword)
        .isAdmin(false)
        .build();
    this.userRepository.save(user);
  }
}

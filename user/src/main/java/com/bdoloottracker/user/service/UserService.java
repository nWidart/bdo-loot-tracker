package com.bdoloottracker.user.service;


import com.bdoloottracker.user.entity.User;
import com.bdoloottracker.user.repository.UserRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
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
}

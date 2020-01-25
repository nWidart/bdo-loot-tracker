package com.bdoloottracker.user.security;

import com.bdoloottracker.user.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class SimpleUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  public SimpleUserDetailsService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(final String email) {
    return userRepository.findByEmail(email)
        .map(SimpleLoginUser::new)
        .orElseThrow(() -> new UsernameNotFoundException("user not found"));
  }
}

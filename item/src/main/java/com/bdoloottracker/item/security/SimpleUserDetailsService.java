package com.bdoloottracker.item.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.client.RestTemplate;

public class SimpleUserDetailsService implements UserDetailsService {

  private final RestTemplate restTemplate;

  public SimpleUserDetailsService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public UserDetails loadUserByUsername(final String email) {
    FindUserResponse response = restTemplate
        .getForObject("http://localhost:8081/public/api/user/find-by-email", FindUserResponse.class);
    if (response == null) {
      throw new UsernameNotFoundException("user not found");
    }
    return SimpleLoginUser.from(response);
  }
}

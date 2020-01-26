package com.bdoloottracker.user.controller;

import com.bdoloottracker.user.entity.User;
import com.bdoloottracker.user.security.SimpleLoginUser;
import java.security.Principal;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/me")
public class MeController {

  @GetMapping
  public User me(Principal principal) {
    SimpleLoginUser simpleLoginUser = (SimpleLoginUser) ((UsernamePasswordAuthenticationToken) principal)
        .getPrincipal();
    return simpleLoginUser.getUser();
  }
}

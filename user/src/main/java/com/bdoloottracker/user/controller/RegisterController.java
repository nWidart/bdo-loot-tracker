package com.bdoloottracker.user.controller;

import com.bdoloottracker.user.request.RegisterUserRequest;
import com.bdoloottracker.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/api/register")
public class RegisterController {

  private final UserService userService;

  public RegisterController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  @CrossOrigin("*")
  public ResponseEntity<String> register(@RequestBody RegisterUserRequest request) {
    this.userService.register(request);

    return ResponseEntity.ok("Register completed. You may now login");
  }
}

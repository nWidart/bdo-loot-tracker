package com.bdoloottracker.user.controller;

import com.bdoloottracker.user.dto.ApiResponse;
import com.bdoloottracker.user.request.RegisterUserRequest;
import com.bdoloottracker.user.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/api/register")
public class RegisterController {

  private final AuthService authService;

  public RegisterController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping
  @CrossOrigin("*")
  public ResponseEntity<ApiResponse> register(@RequestBody RegisterUserRequest request) {
    this.authService.registerUser(request);

    return ResponseEntity.ok(new ApiResponse(true, "Register completed. You may now login"));
  }
}

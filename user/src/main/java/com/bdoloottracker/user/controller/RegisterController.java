package com.bdoloottracker.user.controller;

import com.bdoloottracker.user.dto.ApiResponse;
import com.bdoloottracker.user.event.OnUserRegistrationCompletedEvent;
import com.bdoloottracker.user.exception.UserRegistrationException;
import com.bdoloottracker.user.request.RegisterUserRequest;
import com.bdoloottracker.user.service.AuthService;
import org.springframework.context.ApplicationEventPublisher;
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
  private final ApplicationEventPublisher eventPublisher;

  public RegisterController(AuthService authService, ApplicationEventPublisher eventPublisher) {
    this.authService = authService;
    this.eventPublisher = eventPublisher;
  }

  @PostMapping
  @CrossOrigin("*")
  public ResponseEntity<ApiResponse> register(@RequestBody RegisterUserRequest request) {
    return this.authService.registerUser(request)
        .map(user -> {
          this.eventPublisher.publishEvent(new OnUserRegistrationCompletedEvent(user));
          return ResponseEntity.ok(new ApiResponse(true, "Register completed. You may now login"));
        })
        .orElseThrow(() -> new UserRegistrationException(request.getEmail(), "Missing user object in database"));
  }
}

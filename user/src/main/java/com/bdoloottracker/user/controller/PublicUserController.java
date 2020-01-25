package com.bdoloottracker.user.controller;

import com.bdoloottracker.user.entity.User;
import com.bdoloottracker.user.service.UserService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/public/api/user")
public class PublicUserController {

  private final UserService userService;

  public PublicUserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users/{userId}")
  public FindByEmailResponse findOne(@PathVariable Long userId) {
    return userService.findById(userId).map(FindByEmailResponse::new)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  @GetMapping(
      value = "/find-by-email",
      params = {"email"}
  )
  public FindByEmailResponse findByEmail(@RequestParam("email") String email) {
    return userService.findByEmail(email).map(FindByEmailResponse::new)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  @Data
  @AllArgsConstructor
  @JsonIgnoreProperties(ignoreUnknown = true)
  private static class FindByEmailResponse {

    private Long id;
    private String name;
    private String email;
    private boolean isAdmin;

    public FindByEmailResponse(User user) {
      this.id = user.getId();
      this.name = user.getName();
      this.email = user.getEmail();
      this.isAdmin = user.getIsAdmin();
    }
  }
}

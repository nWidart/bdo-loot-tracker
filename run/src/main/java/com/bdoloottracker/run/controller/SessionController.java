package com.bdoloottracker.run.controller;

import com.bdoloottracker.run.entity.Session;
import com.bdoloottracker.run.exception.SessionNotFoundException;
import com.bdoloottracker.run.request.CreateSessionRequest;
import com.bdoloottracker.run.service.SessionService;
import com.bdoloottracker.securitystarter.SimpleLoginUser;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/run")
public class SessionController {

  private final SessionService sessionService;

  public SessionController(SessionService sessionService) {
    this.sessionService = sessionService;
  }

  @GetMapping("sessions")
  public List<Session> getSessions(@AuthenticationPrincipal SimpleLoginUser user) {
    return sessionService.findAllFor(user);
  }

  @PostMapping("sessions")
  @ResponseStatus(code = HttpStatus.CREATED)
  public Session create(@RequestBody @Valid CreateSessionRequest request) {
    return sessionService.store(request);
  }

  @GetMapping("sessions/{sessionId}")
  public Session show(@PathVariable Long sessionId) {
    return sessionService.findById(sessionId).orElseThrow(SessionNotFoundException::new);
  }
}

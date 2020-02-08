package com.bdoloottracker.run.service;

import com.bdoloottracker.run.entity.Session;
import com.bdoloottracker.run.repository.SessionRepository;
import com.bdoloottracker.run.request.CreateSessionRequest;
import com.bdoloottracker.securitystarter.SimpleLoginUser;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

  private final SessionRepository sessionRepository;

  public SessionService(SessionRepository sessionRepository) {
    this.sessionRepository = sessionRepository;
  }

  public List<Session> findAllFor(SimpleLoginUser user) {
    return sessionRepository.findAllByUserId(user.getUser().getId().intValue());
  }

  public Session store(CreateSessionRequest request) {
    Session session = request.toModel();

    return sessionRepository.save(session);
  }

  public Optional<Session> findById(Long sessionId) {
    return sessionRepository.findById(sessionId);
  }
}

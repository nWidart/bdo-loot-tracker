package com.bdoloottracker.run.service;

import com.bdoloottracker.run.entity.Session;
import com.bdoloottracker.run.repository.SessionRepository;
import com.bdoloottracker.run.security.SimpleLoginUser;
import java.util.List;
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
}

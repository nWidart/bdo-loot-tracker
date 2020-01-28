package com.bdoloottracker.run.repository;

import com.bdoloottracker.run.entity.Session;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, UUID> {

  List<Session> findAllByUserId(Integer userId);
}

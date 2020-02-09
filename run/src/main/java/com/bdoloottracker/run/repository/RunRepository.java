package com.bdoloottracker.run.repository;

import com.bdoloottracker.run.entity.Run;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RunRepository extends JpaRepository<Run, Long> {

  List<Run> findAllBySessionId(Long sessionId);
}

package com.bdoloottracker.run.repository;

import com.bdoloottracker.run.entity.Run;
import com.bdoloottracker.run.entity.RunDrop;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RunDropRepository extends JpaRepository<RunDrop, Long> {

  List<SimpleRunDropProjection> findAllByRun(Run run);
}

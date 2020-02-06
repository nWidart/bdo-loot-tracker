package com.bdoloottracker.run.repository;

import com.bdoloottracker.run.entity.Run;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RunRepository extends JpaRepository<Run, Long> {

}

package com.bdoloottracker.user.repository;

import com.bdoloottracker.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmail(String email);

  Optional<User> findFirstByName(String name);

  boolean existsByEmail(String email);
}

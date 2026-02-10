package com.project.project.repository;

import com.project.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import reactor.core.publisher.Mono;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}

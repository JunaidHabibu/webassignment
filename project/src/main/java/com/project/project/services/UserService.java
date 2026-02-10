package com.project.project.services;

import com.project.project.model.User;
import com.project.project.repository.UserRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class UserService {

    public final UserRepository userRepository;

    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }
    public boolean getUser(User user) { return userRepository.existsByEmail(user.getEmail()); }

    public User registerUser(User user) {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User already exists");
        }

        return userRepository.save(user);
    }

    public Mono<String> getUserFirstName(String email) {
        return Mono.fromSupplier(() ->
                userRepository.findByEmail(email)
                        .map(User::getFirstname)
                        .orElseThrow(() -> new RuntimeException("User not found"))
        );
    }
}

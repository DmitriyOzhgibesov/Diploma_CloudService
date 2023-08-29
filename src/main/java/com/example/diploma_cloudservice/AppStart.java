package com.example.diploma_cloudservice;

import com.example.diploma_cloudservice.Entity.User;
import com.example.diploma_cloudservice.Repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AppStart implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        userRepository.save(User.builder()
                .login("user1@gg.ru")
                .password(passwordEncoder.encode("pass1"))
                .build());
        userRepository.save(User.builder()
                .login("user2@gg.ru")
                .password(passwordEncoder.encode("pass2"))
                .build());
        userRepository.save(User.builder()
                .login("user3@gg.ru")
                .password(passwordEncoder.encode("pass3"))
                .build());
    }
}

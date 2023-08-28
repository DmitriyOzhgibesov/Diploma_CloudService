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
                .login("address1@mail.ru")
                .password(passwordEncoder.encode("p111"))
                .build());
        userRepository.save(User.builder()
                .login("address2@mail.ru")
                .password(passwordEncoder.encode("p222"))
                .build());
        userRepository.save(User.builder()
                .login("address3@mail.ru")
                .password(passwordEncoder.encode("p333"))
                .build());
    }
}

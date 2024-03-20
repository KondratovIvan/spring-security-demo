package com.example.springsecuritydemo.services;


import com.example.springsecuritydemo.model.Application;
import com.example.springsecuritydemo.model.User;
import com.example.springsecuritydemo.repository.UserRepository;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class ApplicationService {
    private List<Application> applications;
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void uploadAppInDB() {
        Faker faker = new Faker();
        applications = IntStream.rangeClosed(1,100)
                .mapToObj(i-> Application.builder()
                    .id(i)
                    .name(faker.app().name())
                    .author(faker.app().author())
                    .version(faker.app().version())
                    .build())
                .toList();
    }

    public List<Application> getAllApplications(){
        return applications;
    }

    public Application getApplicationById(int id){
        return applications.stream()
                .filter(application -> application.getId()==id)
                .findFirst()
                .orElse(null);
    }

    public User addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

}

package com.example.springsecuritydemo.services;


import com.example.springsecuritydemo.model.Application;
import com.github.javafaker.Faker;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class ApplicationService {
    private List<Application> applications = new ArrayList<>();

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

}

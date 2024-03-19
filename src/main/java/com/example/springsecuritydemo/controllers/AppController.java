package com.example.springsecuritydemo.controllers;

import com.example.springsecuritydemo.model.Application;
import com.example.springsecuritydemo.services.ApplicationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/apps")
@AllArgsConstructor
public class AppController {
    private final ApplicationService applicationService;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to the unprotected page";
    }

    @GetMapping
    public List<Application> getAllApplications(){
        return applicationService.getAllApplications();
    }

    @GetMapping(params = {"id"})
    public Application getApplicationById(@PathVariable int id){
        return applicationService.getApplicationById(id);
    }
}

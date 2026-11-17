package com.employees.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityTestController {

    @GetMapping("/dashboard")
    public String dashboard() {
        return "Welcome to Employee Management Dashboard!";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Welcome Admin!";
    }

    @GetMapping("/profile")
    public String profile() {
        return "Authenticated User Profile";
    }

    @GetMapping("/reports")
    public String reports() {
        return "Employee Reports";
    }

    @GetMapping("/settings")
    public String settings() {
        return "Application Settings";
    }
}

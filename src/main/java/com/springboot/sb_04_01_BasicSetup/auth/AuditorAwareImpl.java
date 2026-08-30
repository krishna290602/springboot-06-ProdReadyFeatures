package com.springboot.sb_04_01_BasicSetup.auth;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String>{
    @Override
    public Optional<String> getCurrentAuditor() {
//        return Optional.of(System.getProperty("user.name"));
        // get Security Context
        // get Authentication
        // get the principle
        // get the Username
        return Optional.of("krishna@gmail.com");
    }
}

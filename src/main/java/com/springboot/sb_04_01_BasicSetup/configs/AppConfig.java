package com.springboot.sb_04_01_BasicSetup.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}

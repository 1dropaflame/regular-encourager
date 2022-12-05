package com.dropaflame.config;

import com.dropaflame.encourage.meditate.Quotations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public Quotations quotations() {
        return new Quotations();
    }
}

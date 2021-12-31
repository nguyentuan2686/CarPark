package com.example.car_park.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @Author TuanNA
 * @Date 23/12/2021 10:53 PM
 * @Version 1.0
 */
public class EntityAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("TuấnNA");
    }
}

package com.example.car_park.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @Author TuanNA
 * @Date 23/12/2021 10:52 PM
 * @Version 1.0
 */
@EnableJpaAuditing
@Configuration
public class AuditConfiguration {
    @Bean
    public AuditorAware<String> auditorAware() {
        return new EntityAuditorAware();
    }
}

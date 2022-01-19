package com.example.car_park;

import com.example.car_park.repo.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class CarParkApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarParkApplication.class, args);
    }

}

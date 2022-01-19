package com.example.car_park.repo;

import com.example.car_park.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

/**
 * @Author TuanNA
 * @Date 18/01/2022 11:00 PM
 * @Version 1.0
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT u FROM User u where u.username = ?1")
    Optional<User> findByUserName(String username);
}

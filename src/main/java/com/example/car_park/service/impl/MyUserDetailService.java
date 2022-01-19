package com.example.car_park.service.impl;

import com.example.car_park.entity.User;
import com.example.car_park.exception.MyCustomException;
import com.example.car_park.repo.UserRepository;
import com.example.car_park.security.MyUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author TuanNA
 * @Date 18/01/2022 11:05 PM
 * @Version 1.0
 */
@Service
public class MyUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public MyUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUserName(username);
        optionalUser.orElseThrow(() -> new MyCustomException("User not found"));
        return new MyUserDetails(optionalUser.get());
    }
}

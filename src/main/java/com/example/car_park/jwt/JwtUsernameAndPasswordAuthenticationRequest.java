package com.example.car_park.jwt;

/**
 * @Author TuanNA
 * @Date 19/01/2022 9:40 AM
 * @Version 1.0
 */
public class JwtUsernameAndPasswordAuthenticationRequest {
    private String username;
    private String password;

    public JwtUsernameAndPasswordAuthenticationRequest() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

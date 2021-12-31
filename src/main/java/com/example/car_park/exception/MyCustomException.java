package com.example.car_park.exception;

/**
 * @Author TuanNA
 * @Date 28/12/2021 10:47 AM
 * @Version 1.0
 */
public class MyCustomException extends RuntimeException{
    public MyCustomException(String message) {
        super(message);
    }

    public MyCustomException(String message, Throwable cause) {
        super(message, cause);
    }
}

package com.example.car_park.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

/**
 * @Author TuanNA
 * @Date 24/12/2021 11:39 AM
 * @Version 1.0
 */
@Setter
@Getter
public class GeneralException{
    private HttpStatus httpStatus;
    private String errorMessage;

    public GeneralException() {
    }

    public GeneralException(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public GeneralException(HttpStatus httpStatus, String errorMessage) {
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
    }

}

package com.example.car_park.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author TuanNA
 * @Date 23/12/2021 11:20 PM
 * @Version 1.0
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseModel<T> {
    private Integer code = 0;
    private T data;
}

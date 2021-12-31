package com.example.car_park.dto;

import com.example.car_park.constants.RegexValidate;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * @Author TuanNA
 * @Date 30/12/2021 9:42 AM
 * @Version 1.0
 */
@Data
public class EmployeeDTO {

    private Long id;

    @NotBlank
    private String account;

    private String department;
    private String address;
    private LocalDate birthdate;

    @NotBlank(message = "Email is required")
    @Pattern(regexp = RegexValidate.EMAIL_REGEX, message = "Invalid email, form text@gmail.com")
    private String email;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank
    @Pattern(regexp = RegexValidate.PHONE_REGEX, message = "Invalid phone, 10-11 number, start with 03 or 09")
    private String phone;
    private String gender;

    @NotBlank
    @Size(min = 6, message = "Password length > 6")
    private String password;
    private String role;
}

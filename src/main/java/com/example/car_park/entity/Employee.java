package com.example.car_park.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * @Author TuanNA
 * @Date 23/12/2021 9:19 PM
 * @Version 1.0
 */

@Entity
@ToString
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee extends Auditable<String> implements Serializable {
    @Id
    @Column(columnDefinition = "BIGINT(11)")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(50)")
    private String account;

    @Column(columnDefinition = "VARCHAR(10)")
    private String department;

    @Column(columnDefinition = "VARCHAR(50)")
    private String address;

    private LocalDate birthdate;

    @Column(columnDefinition = "VARCHAR(50)")
    private String email;

    @Column(columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(columnDefinition = "VARCHAR(10)")
    private String phone;

    @Column(columnDefinition = "VARCHAR(10)")
    private String gender;

    @Column(columnDefinition = "VARCHAR(20)")
    private String password;

    @Column(columnDefinition = "VARCHAR(10)")
    private String role;
}

package com.period.myfreeperiod.data.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Cannot be empty")
    @NotNull(message = "Cannot be empty")
    private String firstName;

    @NotBlank(message = "Cannot be empty")
    @NotNull(message = "Cannot be empty")
    private String lastName;

    @NotBlank(message = "Cannot be empty")
    @NotNull(message = "Cannot be empty")
    @Email
    private String email;

    @NotBlank(message = "Cannot be empty")
    @NotNull(message = "Cannot be empty")
    private String password;

}

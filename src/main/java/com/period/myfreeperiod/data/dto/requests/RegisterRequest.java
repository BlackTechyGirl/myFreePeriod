package com.period.myfreeperiod.data.dto.requests;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterRequest {

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}

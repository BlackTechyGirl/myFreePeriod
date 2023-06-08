package com.period.myfreeperiod.data.dto.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginRequest {

    private String email;

    private String password;
}

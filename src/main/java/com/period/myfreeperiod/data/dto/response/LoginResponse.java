package com.period.myfreeperiod.data.dto.response;

import lombok.*;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginResponse {
    private String message;
    private HttpStatus httpStatus;

}

package com.period.myfreeperiod.data.dto.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RegisterResponse {
    private String message;
    private boolean isSuccess;
    private HttpStatus status;
}

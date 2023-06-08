package com.period.myfreeperiod.data.dto.requests;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ChangePasswordRequest {

    private Long id;
    private String currentPassword;
    private String newPassword;

}

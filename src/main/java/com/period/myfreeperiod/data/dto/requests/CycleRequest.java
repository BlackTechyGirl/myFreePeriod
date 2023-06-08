package com.period.myfreeperiod.data.dto.requests;


import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CycleRequest {
    private LocalDate lastPeriodDate;
    private int cycleLength;
    private int flowLength;

}

package com.period.myfreeperiod.service;

import com.period.myfreeperiod.data.model.Cycle;
import com.period.myfreeperiod.data.repository.CycleRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

@Service
@Builder
@AllArgsConstructor
public class CycleServiceImpl implements CycleService{

    private final CycleRepository cycleRepository;

    @Override
    public void saveCycle(Cycle cycle) {

    }
}

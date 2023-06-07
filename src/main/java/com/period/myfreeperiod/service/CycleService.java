package com.period.myfreeperiod.service;

import com.period.myfreeperiod.data.model.Cycle;

import java.util.List;

public interface CycleService {
    void saveCycle(Cycle cycle);
    Cycle getCycleById(Long id);
    List<Cycle> getAllCycle();

    Cycle updateCycle(Cycle cycle);
    void deleteCycle(Long id);


}

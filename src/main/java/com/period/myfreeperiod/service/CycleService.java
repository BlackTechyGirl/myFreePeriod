package com.period.myfreeperiod.service;

import com.period.myfreeperiod.data.model.Cycle;

import java.time.LocalDate;
import java.util.List;

public interface CycleService {
    Cycle saveCycle(Cycle cycle);
    Cycle getCycleById(Long id);
    List<Cycle> getAllCycles();
    Cycle updateCycle(Cycle cycle);
    void deleteCycle(Long id);
    List<LocalDate> getFlowDates(LocalDate lastPeriodDate, int cycleLength, int flowLength);
    LocalDate getOvulationDate(LocalDate lastPeriodDate, int cycleLength, int flowLength);
    List<LocalDate> getFertilityDates(LocalDate lastPeriodDate, int cycleLength, int flowLength);
    List<LocalDate> getNonFertileDates(LocalDate lastPeriodDate, int cycleLength, int flowLength);


}

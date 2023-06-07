package com.period.myfreeperiod.service;

import com.period.myfreeperiod.data.model.Cycle;
import com.period.myfreeperiod.data.repository.CycleRepository;
import com.period.myfreeperiod.exceptions.CycleNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Builder
@AllArgsConstructor
public class CycleServiceImpl implements CycleService{

    private final CycleRepository cycleRepository;

    @Override
    public void saveCycle(Cycle cycle) {
        cycleRepository.save(cycle);
    }

    @Override
    public Cycle getCycleById(Long id) {
        return cycleRepository.findById(id)
                .orElseThrow(() -> new CycleNotFoundException("Cycle with ID " + id + " not found"));    }

    @Override
    public List<Cycle> getAllCycle() {
        return cycleRepository.findAll();
    }

    @Override
    public Cycle updateCycle(Cycle cycle) {
        return cycleRepository.save(cycle);
    }

    @Override
    public void deleteCycle(Long id) {
        cycleRepository.deleteById(id);
    }

    public static List<LocalDate> getFlowDates(LocalDate lastPeriodDate, int cycleLength, int flowLength) {
        LocalDate nextPeriodDate = lastPeriodDate.plusDays(cycleLength);
        LocalDate flowStartDate = nextPeriodDate.minusDays(flowLength);

        List<LocalDate>flowDates = new ArrayList<LocalDate>();
        flowDates.add(nextPeriodDate);
        flowDates.add(flowStartDate);

        return flowDates;
    }

    public static LocalDate getOvulationDate(LocalDate lastPeriodDate, int cycleLength, int flowLength) {
        LocalDate nextPeriodDate = lastPeriodDate.plusDays(cycleLength);
        return nextPeriodDate.minusDays(14);
        //return nextPeriodDate.minusDays(14);
    }

    public static List<LocalDate> getFertilityDates(LocalDate lastPeriodDate, int cycleLength, int flowLength) {

        LocalDate ovulationDate = getOvulationDate(lastPeriodDate, cycleLength, flowLength);
        LocalDate fertilityStarts = ovulationDate.minusDays(5);
        LocalDate fertilityEnds = ovulationDate.plusDays(5);

        List<LocalDate> fertileDates = new ArrayList<>();
        while (fertilityStarts.isBefore(fertilityEnds.plusDays(1))) {
            fertileDates.add(fertilityStarts);
            fertilityStarts = fertilityStarts.plusDays(1);
        }
        return fertileDates;
    }


    public static List<LocalDate> getNonFertileDates(LocalDate lastPeriodDate, int cycleLength, int flowLength) {
        LocalDate ovulationDate = getOvulationDate(lastPeriodDate, cycleLength, flowLength);
//        LocalDate nonFertileStart = lastPeriodDate;

//        nonFertileDates = new ArrayList<>();
        List<LocalDate> dates = new ArrayList<>();
        for (int i = 5; i <=cycleLength-5 ; i++) {
            LocalDate nonFertile = ovulationDate.minusDays(i);
            dates.add(nonFertile);
        }
//
//        while (nonFertileStart.isBefore(nonFertileEnd.plusDays(1))) {
//            nonFertileDates.add(nonFertileStart);
//            nonFertileStart = nonFertileStart.plusDays(1);
//        }
        return dates;
    }
    public void validateCycleData(int cycleLength, LocalDate lastPeriodDate, int flowLength) {
        // Add your validation logic here
    }
}

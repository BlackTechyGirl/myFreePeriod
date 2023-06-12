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
    public Cycle saveCycle(Cycle cycle) {
        cycleRepository.save(cycle);
        return cycle;
    }

    @Override
    public Cycle getCycleById(Long id) {
        return cycleRepository.findById(id)
                .orElseThrow(() -> new CycleNotFoundException("Cycle with ID " + id + " not found"));    }

    @Override
    public List<Cycle> getAllCycles() {
        return cycleRepository.findAll();    }

    @Override
    public Cycle updateCycle(Cycle cycle) {
        return cycleRepository.save(cycle);
    }

    @Override
    public void deleteCycle(Long id) {
        cycleRepository.deleteById(id);
    }

    @Override
    public  List<LocalDate> getFlowDates(LocalDate lastPeriodDate, int cycleLength, int flowLength) {
        validateCycleData(cycleLength, lastPeriodDate, flowLength);
        LocalDate nextPeriodDate = lastPeriodDate.plusDays(cycleLength);
        LocalDate flowStartDate = nextPeriodDate.minusDays(flowLength);

        List<LocalDate>flowDates = new ArrayList<LocalDate>();
        flowDates.add(nextPeriodDate);
        flowDates.add(flowStartDate);

        return flowDates;
    }

    @Override
    public LocalDate getOvulationDate(LocalDate lastPeriodDate, int cycleLength, int flowLength) {
        validateCycleData(cycleLength, lastPeriodDate, flowLength);
        LocalDate nextPeriodDate = lastPeriodDate.plusDays(cycleLength);
        return nextPeriodDate.minusDays(14);
    }

    @Override
    public List<LocalDate> getFertilityDates(LocalDate lastPeriodDate, int cycleLength, int flowLength) {
        validateCycleData(cycleLength, lastPeriodDate, flowLength);

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



    @Override
    public List<LocalDate> getNonFertileDates(LocalDate lastPeriodDate, int cycleLength, int flowLength) {
        validateCycleData(cycleLength, lastPeriodDate, flowLength);
        validateCycleData(cycleLength, lastPeriodDate, flowLength);
        LocalDate ovulationDate = getOvulationDate(lastPeriodDate, cycleLength, flowLength);

        List<LocalDate> dates = new ArrayList<>();
        for (int i = 5; i <=cycleLength-5 ; i++) {
            LocalDate nonFertile = ovulationDate.minusDays(i);
            dates.add(nonFertile);
        }
        return dates;
    }

    private void validateCycleData(int cycleLength, LocalDate lastPeriodDate, int flowLength) {
        // Perform validation checks
        if (cycleLength <= 0) {
            throw new IllegalArgumentException("Cycle length must be greater than 0.");
        }

        if (flowLength <= 0) {
            throw new IllegalArgumentException("Flow length must be greater than 0.");
        }

        if (lastPeriodDate == null) {
            throw new IllegalArgumentException("Last period date cannot be null.");
        }

        // Additional validation checks as per your requirements

        // Example: Ensure that the last period date is not in the future
        LocalDate currentDate = LocalDate.now();
        if (lastPeriodDate.isAfter(currentDate)) {
            throw new IllegalArgumentException("Last period date cannot be in the future.");
        }

        // Example: Ensure that the cycle length is within a reasonable range
        int minCycleLength = 21;
        int maxCycleLength = 45;
        if (cycleLength < minCycleLength || cycleLength > maxCycleLength) {
            throw new IllegalArgumentException("Cycle length should be between " + minCycleLength + " and " + maxCycleLength + " days.");
        }

        // Example: Add more validation checks as per your specific requirements
    }

}

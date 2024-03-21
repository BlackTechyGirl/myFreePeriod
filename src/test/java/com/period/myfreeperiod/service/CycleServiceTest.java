package com.period.myfreeperiod.service;

import com.period.myfreeperiod.data.repository.CycleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CycleServiceTest {


    @Autowired
    private  CycleService cycleService;

    @Autowired
    private CycleRepository cycleRepository;
    private LocalDate lastPeriodDate;
    private int cycleLength;
    private int flowLength;
    @BeforeEach
    void setUp() {
        lastPeriodDate = LocalDate.of(2023, 4, 5);
        cycleLength = 28;
        flowLength = 5;

    }

    @Test
    void getFlowDates() {
        List<LocalDate> expectedFlowDates = List.of(LocalDate.of(2023, 5, 3), LocalDate.of(2023, 4, 28));
        List<LocalDate> actualFlowDates = cycleService.getFlowDates(lastPeriodDate, cycleLength, flowLength);
        assertEquals(expectedFlowDates, actualFlowDates);
    }

    @Test
    void getOvulationDate() {
        LocalDate expectedOvulationDate = LocalDate.of(2023, 4, 19);
        LocalDate actualOvulationDate = cycleService.getOvulationDate(lastPeriodDate, cycleLength, flowLength);
        assertEquals(expectedOvulationDate, actualOvulationDate);

    }

    @Test
    void getFertilityDates() {
        List<LocalDate> expectedFertilityDates = List.of(
                LocalDate.of(2023, 4, 14),
                LocalDate.of(2023, 4, 15),
                LocalDate.of(2023, 4, 16),
                LocalDate.of(2023, 4, 17),
                LocalDate.of(2023, 4, 18),
                LocalDate.of(2023, 4, 19),
                LocalDate.of(2023, 4, 20),
                LocalDate.of(2023, 4, 21),
                LocalDate.of(2023, 4, 22),
                LocalDate.of(2023, 4, 23),
                LocalDate.of(2023, 4, 24)
        );

        List<LocalDate> actualFertilityDates = cycleService.getFertilityDates(lastPeriodDate, cycleLength, flowLength);
        assertEquals(expectedFertilityDates, actualFertilityDates);

    }

    @Test
    void getNonFertileDates() {
        List<LocalDate> expectedDates = List.of(
                LocalDate.of(2023, 4, 14),
                LocalDate.of(2023, 4, 26),
                LocalDate.of(2023, 4, 27),
                LocalDate.of(2023, 4, 28),
                LocalDate.of(2023, 4, 29),
                LocalDate.of(2023, 4, 30),
                LocalDate.of(2023, 5, 1),
                LocalDate.of(2023, 5, 2),
                LocalDate.of(2023, 5, 3),
                LocalDate.of(2023, 5, 10)
        );
        List<LocalDate> actualNonFertileDates = cycleService.getNonFertileDates(lastPeriodDate, cycleLength, flowLength);
        assertEquals(expectedDates, actualNonFertileDates);
    }
}

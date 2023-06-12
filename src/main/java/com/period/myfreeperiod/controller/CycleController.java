package com.period.myfreeperiod.controller;

import com.period.myfreeperiod.data.dto.requests.CycleRequest;
import com.period.myfreeperiod.data.model.Cycle;
import com.period.myfreeperiod.service.CycleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/api/v1/cycles")
@AllArgsConstructor
public class CycleController {
        private final CycleService cycleService;

        @PostMapping("/save")
        public ResponseEntity<?> saveCycle(@RequestBody Cycle cycle) {
            Cycle savedCycle = cycleService.saveCycle(cycle);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedCycle);
        }

        @GetMapping("/{id}")
        public ResponseEntity<Cycle> getCycleById(@PathVariable Long id) {
            Cycle cycle = cycleService.getCycleById(id);
            return ResponseEntity.ok(cycle);
        }

        @GetMapping("/all'")
        public ResponseEntity<?> getAllCycles() {
            List<Cycle> cycles = cycleService.getAllCycles();
            return ResponseEntity.ok(cycles);
        }

        @GetMapping("/user/{userId}")
        public ResponseEntity<?> getCyclesById(@PathVariable Long userId) {
            List<Cycle> cycles = (List<Cycle>) cycleService.getCycleById(userId);
            return ResponseEntity.ok(cycles);
        }

        @PatchMapping("/{id}")
        public ResponseEntity<?> updateCycle(@PathVariable Long id, @RequestBody Cycle updatedCycle) {
            updatedCycle.setId(id);
            Cycle cycle = cycleService.updateCycle(updatedCycle);
            return ResponseEntity.ok(cycle);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteCycle(@PathVariable Long id) {
            cycleService.deleteCycle(id);
            return ResponseEntity.ok("Cycle deleted successfully");
        }

        @GetMapping("/flow-dates")
        public ResponseEntity<?> getFlowDates(@RequestBody CycleRequest request) {
            List<LocalDate> flowDates = cycleService.getFlowDates(
                    request.getLastPeriodDate(),
                    request.getCycleLength(),
                    request.getFlowLength()
            );
            return ResponseEntity.ok(flowDates);
        }

        @GetMapping("/ovulation-date")
        public ResponseEntity<?> getOvulationDate(@RequestBody CycleRequest request) {
            LocalDate ovulationDate = cycleService.getOvulationDate(
                    request.getLastPeriodDate(),
                    request.getCycleLength(),
                    request.getFlowLength()
            );
            return ResponseEntity.ok(ovulationDate);
        }

        @GetMapping("/fertility-dates")
        public ResponseEntity<?> getFertilityDates(@RequestBody CycleRequest request) {
            List<LocalDate> fertilityDates = cycleService.getFertilityDates(
                    request.getLastPeriodDate(),
                    request.getCycleLength(),
                    request.getFlowLength()
            );
            return ResponseEntity.ok(fertilityDates);
        }

        @GetMapping ("/non-fertile-dates")
        public ResponseEntity<?> getNonFertileDates(@RequestBody CycleRequest request) {
            List<LocalDate> nonFertileDates = cycleService.getNonFertileDates(
                    request.getLastPeriodDate(),
                    request.getCycleLength(),
                    request.getFlowLength()
            );
            return ResponseEntity.ok(nonFertileDates);
        }
}


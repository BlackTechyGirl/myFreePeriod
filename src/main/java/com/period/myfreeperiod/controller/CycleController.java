package com.period.myfreeperiod.controller;

import com.period.myfreeperiod.model.Cycle;
import com.period.myfreeperiod.service.CycleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/cycle")
@AllArgsConstructor
public class CycleController {

    private final CycleService cycleService;

    public String saveCycle(Cycle cycle){
        cycleService.saveCycle(cycle);
        return "redirect:/success";
    }
}

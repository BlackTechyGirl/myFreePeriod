package com.period.myfreeperiod.data.repository;

import com.period.myfreeperiod.data.model.Cycle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CycleRepository extends JpaRepository<Cycle, Long> {
}

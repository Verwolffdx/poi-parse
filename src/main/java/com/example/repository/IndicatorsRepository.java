package com.example.repository;

import com.example.entity.Indicators;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IndicatorsRepository extends JpaRepository<Indicators, Long> {
    Indicators findByIndicatorname(String indicatorname);
}

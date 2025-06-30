package com.fintech.currency_converter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintech.currency_converter.model.ConversionLog;

public interface ConversionLogRepository extends JpaRepository<ConversionLog, Long> {

}

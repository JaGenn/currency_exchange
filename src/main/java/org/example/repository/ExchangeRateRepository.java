package org.example.repository;

import org.example.entity.ExchangeRate;

import java.util.List;
import java.util.Optional;

public interface ExchangeRateRepository extends CrudRepository<ExchangeRate, Long>{

    Optional<ExchangeRate> findByCodes(String baseCurCode, String targetCurCode);

    void updateRate(ExchangeRate exchangeRate);
}

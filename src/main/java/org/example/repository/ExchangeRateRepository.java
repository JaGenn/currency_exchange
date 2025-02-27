package org.example.repository;

import org.example.dto.ExchangeRateDto;
import org.example.entity.CurrencyEntity;
import org.example.entity.ExchangeRate;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ExchangeRateRepository {


    Set<ExchangeRate> selectAll();

    Optional<ExchangeRate> findByCodes(String baseCurCode, String targetCurCode);


}

package org.example.service;

import org.example.entity.ExchangeRate;
import org.example.repository.CurrencyRepository;
import org.example.repository.CurrencyRepositoryImpl;
import org.example.repository.ExchangeRateRepository;
import org.example.repository.ExchangeRateRepositoryImpl;

import java.util.List;
import java.util.Optional;



public class ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository = new ExchangeRateRepositoryImpl();
    private final CurrencyRepository currencyRepository = new CurrencyRepositoryImpl();

    public List<ExchangeRate> selectAll() {
        return exchangeRateRepository.selectAll();
    }

    public Optional<ExchangeRate> findByCodes(String baseCurCode, String targetCurCode) {
        return exchangeRateRepository.findByCodes(baseCurCode, targetCurCode);
    }

    public ExchangeRate save(ExchangeRate exchangeRate) {
        return exchangeRateRepository.save(exchangeRate);
    }

    public void updateRate(ExchangeRate exchangeRate) {
        exchangeRateRepository.updateRate(exchangeRate);
    }

    public void delete(long id) {
        exchangeRateRepository.delete(id);
    }

}

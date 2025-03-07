package org.example.service;

import org.example.entity.ExchangeRate;
import org.example.exception.NotFoundException;
import org.example.repository.ExchangeRateRepository;
import org.example.repository.ExchangeRateRepositoryImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public class ExchangeService {

    private final ExchangeRateRepository exchangeRateRepository = new ExchangeRateRepositoryImpl();


    public BigDecimal exchange(String from, String to, BigDecimal amount) {

        if (from.equals(to)) {
            return amount;
        }

        BigDecimal rate = findCourse(from, to);

        if (rate != null) {
            return amount.multiply(rate);
        }

        rate = findReverseCourse(from, to);
        if (rate != null) {
            return amount.divide(rate, 3, RoundingMode.HALF_UP);
        }

        rate = findCrossCourse(from, to);
        return amount.multiply(rate);
    }


    private BigDecimal findCourse(String from, String to) {
        Optional<ExchangeRate> exchangeRate = exchangeRateRepository.findByCodes(from, to);
        return (!exchangeRate.isEmpty()) ? exchangeRate.get().getRate() : null;
    }

    private BigDecimal findReverseCourse(String from, String to) {
        Optional<ExchangeRate> exchangeRate = exchangeRateRepository.findByCodes(to, from);
        return (!exchangeRate.isEmpty()) ? exchangeRate.get().getRate() : null;
    }

    private BigDecimal findCrossCourse(String from, String to) {
        String commonCurrency = "USD";
        Optional<ExchangeRate> fromExchange = exchangeRateRepository.findByCodes(commonCurrency, from);
        Optional<ExchangeRate> toExchange = exchangeRateRepository.findByCodes(commonCurrency, to);

        if (fromExchange.isEmpty() || toExchange.isEmpty()) {
            throw new NotFoundException(String.format("Exchange rate '%s' - '%s' not found in the database", from, to));
        }

        return toExchange.get().getRate().divide(fromExchange.get().getRate(), 3, RoundingMode.HALF_UP);
    }
}

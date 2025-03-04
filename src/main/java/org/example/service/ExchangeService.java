package org.example.service;

import org.example.entity.ExchangeRate;
import org.example.exception.DataBaseOperationErrorException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public class ExchangeService {

    private final ExchangeRateService exchangeRateService = new ExchangeRateService();


    public BigDecimal exchange(String from, String to, BigDecimal amount) {
        BigDecimal result;
        BigDecimal rate;

        if (findCourse(from, to) != null) {
            rate = findCourse(from, to);
            result = amount.multiply(rate);
            return result;
        } else if (findReverseCourse(from, to) != null) {
            rate = findReverseCourse(from, to);
            result = amount.divide(rate, 3, RoundingMode.HALF_UP);
            return result;
        } else {
            rate = findCrossCourse(from, to);
            result = amount.multiply(rate);
            return result;
        }

    }


    private BigDecimal findCourse(String from, String to) {
        Optional<ExchangeRate> exchangeRate = exchangeRateService.findByCodes(from, to);
        if (!exchangeRate.isEmpty()) {
            return exchangeRate.get().getRate();
        } else {
            return null;
        }
    }

    private BigDecimal findReverseCourse(String from, String to) {
        Optional<ExchangeRate> exchangeRate = exchangeRateService.findByCodes(to, from);
        if (!exchangeRate.isEmpty()) {
            return exchangeRate.get().getRate();
        } else {
            return null;
        }
    }

    private BigDecimal findCrossCourse(String from, String to) {
        String commonCurrency = "USD";
        Optional<ExchangeRate> fromExchange = exchangeRateService.findByCodes(commonCurrency, from);
        Optional<ExchangeRate> toExchange = exchangeRateService.findByCodes(commonCurrency, to);

        if (fromExchange.isEmpty() || toExchange.isEmpty()) {
            throw new DataBaseOperationErrorException("No available exchange rate for " + from + " to " + to);
        }

        return toExchange.get().getRate().divide(fromExchange.get().getRate(), 3, RoundingMode.HALF_UP);
    }
}

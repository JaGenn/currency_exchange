package org.example.Utils;

import org.example.dto.ExchangeRateDto;
import org.example.entity.ExchangeRate;
import org.example.exception.InvalidParameterException;

import java.math.BigDecimal;

public class Converter {

    public static ExchangeRateDto convertToDto(ExchangeRate exchangeRate) {
        return new ExchangeRateDto(exchangeRate.getId(),
                                exchangeRate.getBaseCurrency().getCode() +
                                exchangeRate.getTargetCurrency().getCode(),
                                exchangeRate.getRate());
    }

    public static BigDecimal convertToNumber(String rate) {

        if (rate == null || rate.isBlank()) {
            throw new InvalidParameterException("Missing parameter - rate");
        }

        try {
            return BigDecimal.valueOf(Double.parseDouble(rate));
        }
        catch (NumberFormatException e) {
            throw new InvalidParameterException("Parameter rate must be a number");
        }
    }


}

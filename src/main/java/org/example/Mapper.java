package org.example;

import org.example.dto.ExchangeRateDto;
import org.example.entity.ExchangeRate;

public class Mapper {

    public ExchangeRateDto convertToDto(ExchangeRate exchangeRate) {
        return new ExchangeRateDto(exchangeRate.getId(),
                                exchangeRate.getBaseCurrency().getCode() +
                                exchangeRate.getTargetCurrency().getCode(),
                                exchangeRate.getRate());
    }


}

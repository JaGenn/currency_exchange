package org.example.service;

import org.example.dto.ExchangeRateDto;
import org.example.entity.ExchangeRate;
import org.example.exception.NotFoundException;
import org.example.repository.ExchangeRateRepository;
import org.example.repository.ExchangeRateRepositoryImpl;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


public class ExchangeRateService {

    private ExchangeRateRepository exchangeRateRepository = new ExchangeRateRepositoryImpl();

    public Set<ExchangeRateDto> selectAll() {

        Set<ExchangeRateDto> exchangeRates = exchangeRateRepository.selectAll().stream()
                .map(exchangeRate -> new ExchangeRateDto(
                        exchangeRate.getId(),
                        exchangeRate.getBaseCurrency().getCode() + " " +
                                exchangeRate.getTargetCurrency().getCode(),
                        exchangeRate.getRate()
                ))
                .collect(Collectors.toSet());
        return exchangeRates;
    }

    public ExchangeRateDto findByCodes (String baseCurCode, String targetCurCode) {
//        todo: сейчас будет выходить DTO, потом сделать не DTO, и все преображать в JSON как было в ТЗ
        Optional<ExchangeRate> exchangeRate = exchangeRateRepository.findByCodes(baseCurCode, targetCurCode);
        if (exchangeRate.isPresent()) {
            return new ExchangeRateDto(exchangeRate.get().getId(),
                            exchangeRate.get().getBaseCurrency().getCode() + exchangeRate.get().getTargetCurrency().getCode(),
                                        exchangeRate.get().getRate());
        } else {
            throw new NotFoundException("Response of findByCodes-method is empty");
        }
    }

}

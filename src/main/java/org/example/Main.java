package org.example;

import org.example.repository.CurrencyRepository;
import org.example.repository.CurrencyRepositoryImpl;
import org.example.entity.CurrencyEntity;

public class Main {

    private static final CurrencyRepository currencyRepository = new CurrencyRepositoryImpl();

    public static void main(String[] args) {


        CurrencyEntity entity = new CurrencyEntity("TRY", "Turkish Lira", "₺");
        CurrencyEntity newEntity = currencyRepository.save(entity);

    }
}

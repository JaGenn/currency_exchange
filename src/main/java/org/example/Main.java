package org.example;

import org.example.repository.CurrencyRepository;
import org.example.repository.CurrencyRepositoryImpl;
import org.example.entity.CurrencyEntity;

import java.util.List;
import java.util.Optional;

public class Main {

    private static final CurrencyRepository currencyRepository = new CurrencyRepositoryImpl();

    public static void main(String[] args) {


        currencyRepository.delete(12);

    }
}

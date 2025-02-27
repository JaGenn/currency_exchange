package org.example.repository;

import org.example.entity.CurrencyEntity;

import java.util.List;
import java.util.Optional;

public interface CurrencyRepository {

    List<CurrencyEntity> selectAll();

    Optional<CurrencyEntity> findById(long id);

    CurrencyEntity save(CurrencyEntity currency);

    void delete(long id);

    Optional<CurrencyEntity> findByCode(String code);
}

package org.example.repository;

import org.example.entity.CurrencyEntity;

import java.util.List;
import java.util.Optional;

public interface CurrencyRepository extends CrudRepository<CurrencyEntity, Long> {

    Optional<CurrencyEntity> findById(long id);

    Optional<CurrencyEntity> findByCode(String code);
}

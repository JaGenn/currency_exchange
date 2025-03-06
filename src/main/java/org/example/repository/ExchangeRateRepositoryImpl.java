package org.example.repository;

import org.example.Utils.DataBase;
import org.example.entity.CurrencyEntity;
import org.example.entity.ExchangeRate;
import org.example.exception.DataBaseOperationErrorException;
import org.example.exception.NotFoundException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ExchangeRateRepositoryImpl implements ExchangeRateRepository {

    private final CurrencyRepository currencyRepository = new CurrencyRepositoryImpl();

    @Override
    public List<ExchangeRate> selectAll() {

        List<ExchangeRate> exchanges = new ArrayList<>();

        String query = "select * from exchangeRates";

        try (Connection connection = DataBase.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                long id = resultSet.getLong("id");
                long baseCurId = resultSet.getLong("base_currency_id");
                long targetCurId = resultSet.getLong("target_currency_id");
                BigDecimal rate = resultSet.getBigDecimal("rate");
                Optional<CurrencyEntity> baseCurrency = currencyRepository.findById(baseCurId);
                Optional<CurrencyEntity> targetCurrency = currencyRepository.findById(targetCurId);
                if (baseCurrency.isPresent() && targetCurrency.isPresent()) {
                    exchanges.add(new ExchangeRate(id, baseCurrency.get(), targetCurrency.get(), rate));
                }

            }

        } catch (SQLException e) {
            throw new DataBaseOperationErrorException("Select exchanges error: " + e.getMessage());
        }
        return exchanges;
    }

    @Override
    public Optional<ExchangeRate> findByCodes(String baseCurCode, String targetCurCode) {
        long baseId, targetId;
        Optional<CurrencyEntity> baseCur = currencyRepository.findByCode(baseCurCode);
        Optional<CurrencyEntity> targetCur = currencyRepository.findByCode(targetCurCode);

        if (baseCur.isPresent() && targetCur.isPresent()) {
            baseId = baseCur.get().getId();
            targetId = targetCur.get().getId();
        } else {
            throw new NotFoundException("There was no currencies by this codes id Data Base");
        }

        String query = "SELECT * FROM exchangeRates WHERE base_currency_id = ? AND target_currency_id = ?";

        try (Connection connection = DataBase.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, baseId);
            statement.setLong(2, targetId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                ExchangeRate exchangeRate = new ExchangeRate(
                        resultSet.getLong("id"),
                        baseCur.get(),
                        targetCur.get(),
                        resultSet.getBigDecimal("rate")
                );
                return Optional.of(exchangeRate);
            } else {
                return Optional.empty();
            }

        } catch (SQLException e) {
            throw new DataBaseOperationErrorException(e.getMessage());
        }
    }

    @Override
    public ExchangeRate save(ExchangeRate exchangeRate) {
        String query = "insert into exchangeRates (base_currency_id, target_currency_id, rate) values (?, ?, ?)";

        try (Connection connection = DataBase.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, exchangeRate.getBaseCurrency().getId());
            statement.setLong(2, exchangeRate.getTargetCurrency().getId());
            statement.setBigDecimal(3, exchangeRate.getRate());
            statement.executeUpdate();
            return exchangeRate;
        } catch (SQLException e) {
            throw new DataBaseOperationErrorException(e.getMessage());
        }
    }

    @Override
    public void updateRate(ExchangeRate exchangeRate) {

        String query = "UPDATE exchangeRates SET rate = ? WHERE id = ?";

        try (Connection connection = DataBase.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setBigDecimal(1, exchangeRate.getRate());
            statement.setLong(2, exchangeRate.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DataBaseOperationErrorException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        String query = "delete from exchangeRates where id = ?";

        try (Connection connection = DataBase.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, id);

            statement.executeUpdate();

            System.out.println("Entity deleted");
        } catch (SQLException e) {
            throw new DataBaseOperationErrorException("Delete failed: " + e.getMessage());
        }
    }
}

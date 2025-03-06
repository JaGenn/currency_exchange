package org.example.repository;

import org.example.Utils.DataBase;
import org.example.entity.CurrencyEntity;
import org.example.exception.DataBaseOperationErrorException;
import org.example.exception.NotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CurrencyRepositoryImpl implements CurrencyRepository {

    @Override
    public List<CurrencyEntity> selectAll() {

        List<CurrencyEntity> currencies = new ArrayList<>();

        String query = "select * from currencies";

        try (Connection connection = DataBase.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                long id = resultSet.getLong("id");
                String code = resultSet.getString("code");
                String fullName = resultSet.getString("full_name");
                String sign = resultSet.getString("sign");

                currencies.add(new CurrencyEntity(id, code, fullName, sign));
            }

            return currencies;

        } catch (SQLException e) {
            throw new DataBaseOperationErrorException("Select currencies error: " + e.getMessage());
        }

    }

    @Override
    public Optional<CurrencyEntity> findById(long id) {

        String query = "select * from currencies where id = ?";

        try (Connection connection = DataBase.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                long Id = resultSet.getLong("id");
                String code = resultSet.getString("code");
                String fullName = resultSet.getString("full_name");
                String sign = resultSet.getString("sign");
                CurrencyEntity entity = new CurrencyEntity(Id, code, fullName, sign);
                return Optional.of(entity);

            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new NotFoundException("There is no entity with id " + id + " in DB");
        }

    }

    @Override
    public CurrencyEntity save(CurrencyEntity currency) {
        String query = "insert into currencies (code, full_name, sign) values (?, ?, ?)";

        try (Connection connection = DataBase.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, currency.getCode());
            statement.setString(2, currency.getFullName());
            statement.setString(3, currency.getSign());
            statement.executeUpdate();
            return currency;
        } catch (SQLException e) {
            throw new DataBaseOperationErrorException("Save entity is failed: " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {

        String query = "delete from currencies where id = ?";

        try (Connection connection = DataBase.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, id);

            statement.executeUpdate();

            System.out.println("Entity deleted");
        } catch (SQLException e) {
            throw new DataBaseOperationErrorException("Delete failed: " + e.getMessage());
        }

    }

    @Override
    public Optional<CurrencyEntity> findByCode(String code) {

        String query = "select * from currencies where code = ?";

        try (Connection connection = DataBase.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, code);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {

                long id = resultSet.getLong("id");
                String fullName = resultSet.getString("full_name");
                String sign = resultSet.getString("sign");
                CurrencyEntity entity = new CurrencyEntity(id, code, fullName, sign);
                return Optional.of(entity);

            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new NotFoundException("There is no entity with code " + code + " in DB");
        }
    }
}

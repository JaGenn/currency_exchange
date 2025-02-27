package org.example;

import org.example.exception.DataBaseOperationErrorException;
import org.example.repository.CurrencyRepository;
import org.example.repository.CurrencyRepositoryImpl;
import org.example.entity.CurrencyEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class Main {

    private static final CurrencyRepository currencyRepository = new CurrencyRepositoryImpl();

    public static void main(String[] args) {


        String query = "UPDATE exchangeRates\n" +
                "SET target_currency_id = 9\n" +
                "WHERE id = 6";

        try (Connection connection = DataBase.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.executeUpdate();

            int resultSet = statement.executeUpdate();

            System.out.println("Entity deleted");
        } catch (SQLException e) {
            throw new DataBaseOperationErrorException("Delete failed: " + e.getMessage());
        }

    }
}

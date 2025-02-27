package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.entity.CurrencyEntity;
import org.example.exception.NotFoundException;
import org.example.repository.CurrencyRepository;
import org.example.repository.CurrencyRepositoryImpl;

import java.io.IOException;

@WebServlet("/currency/*")
public class CurrencyServlet extends HttpServlet {

    private CurrencyRepository currencyRepository = new CurrencyRepositoryImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String code = req.getPathInfo().replaceFirst("/", "");

        CurrencyEntity currency = currencyRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException("Currency with code '" + code + "' not found"));

        resp.getWriter().write(currency.getCode() + " " + currency.getSign());
    }
}

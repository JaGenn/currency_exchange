package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Utils.Converter;
import org.example.dto.ExchangeRateDto;
import org.example.entity.CurrencyEntity;
import org.example.entity.ExchangeRate;
import org.example.repository.CurrencyRepository;
import org.example.repository.CurrencyRepositoryImpl;
import org.example.repository.ExchangeRateRepository;
import org.example.repository.ExchangeRateRepositoryImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet("/exchangeRates")
public class ExchangeRatesServlet extends HttpServlet {

    private final ExchangeRateRepository exchangeRateRepository = new ExchangeRateRepositoryImpl();
    private final CurrencyRepository currencyRepository = new CurrencyRepositoryImpl();




    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ExchangeRate> exchangeRates = exchangeRateRepository.selectAll();
        List<ExchangeRateDto> exchangeRateDtos = exchangeRates.stream()
                .map(Converter::convertToDto).collect(Collectors.toList());
        req.setAttribute("exList", exchangeRateDtos);

        List<CurrencyEntity> currencies = currencyRepository.selectAll();
        req.setAttribute("currencies", currencies);

        req.getServletContext().getRequestDispatcher("/exchange.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String baseCurCode = req.getParameter("baseCurrency");
        String targetCurCode = req.getParameter("targetCurrency");
        BigDecimal rate = new BigDecimal(req.getParameter("rate").replace(",", "."));


        Optional<CurrencyEntity> base = currencyRepository.findByCode(baseCurCode);
        Optional<CurrencyEntity> target = currencyRepository.findByCode(targetCurCode);
        exchangeRateRepository.save(new ExchangeRate(base.get(), target.get(), rate));
        resp.sendRedirect(req.getContextPath() + "/exchangeRates");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        exchangeRateRepository.delete(id);
        resp.sendRedirect(req.getContextPath() + "/exchangeRates");
    }

}

package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Mapper;
import org.example.dto.ExchangeRateDto;
import org.example.entity.CurrencyEntity;
import org.example.entity.ExchangeRate;
import org.example.repository.CurrencyRepository;
import org.example.repository.CurrencyRepositoryImpl;
import org.example.service.ExchangeRateService;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@WebServlet("/exchangeRates")
public class ExchangeRatesServlet extends HttpServlet {

    private static final ExchangeRateService exchangeRateService = new ExchangeRateService();
    private static final CurrencyRepository currencyRepository = new CurrencyRepositoryImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ExchangeRateDto> exchangeRates = exchangeRateService.selectAll();
        req.setAttribute("exList", exchangeRates);

        List<CurrencyEntity> currencies = currencyRepository.selectAll();
        req.setAttribute("currencies", currencies);

        req.getServletContext().getRequestDispatcher("/exchange.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String baseCurCode = req.getParameter("baseCurrency");
        String targetCurCode = req.getParameter("targetCurrency");
        System.out.println(req.getContextPath());
        String rate = req.getParameter("rate");

        Optional<CurrencyEntity> base = currencyRepository.findByCode(baseCurCode);
        Optional<CurrencyEntity> target = currencyRepository.findByCode(targetCurCode);
        exchangeRateService.save(new ExchangeRate(base.get(), target.get(), BigDecimal.valueOf(Long.parseLong(rate))));
        resp.sendRedirect(req.getContextPath() + "/exchangeRates");
    }


}

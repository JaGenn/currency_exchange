package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dto.ExchangeRateDto;
import org.example.repository.ExchangeRateRepository;
import org.example.repository.ExchangeRateRepositoryImpl;
import org.example.service.ExchangeRateService;

import java.io.IOException;

@WebServlet("/exchangeRate/*")
public class ExchangeRateServlet extends HttpServlet {

    private ExchangeRateService exchangeRateService = new ExchangeRateService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getPathInfo().replaceFirst("/", "");
        String baseCur = code.substring(0, 3);
        String targetCur = code.substring(3);

        ExchangeRateDto exchangeRateDto = exchangeRateService.findByCodes(baseCur, targetCur);
        resp.getWriter().println(exchangeRateDto.getRate());
    }
}

package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dto.ExchangeRateDto;
import org.example.entity.ExchangeRate;
import org.example.service.ExchangeRateService;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.spec.ECField;
import java.util.Optional;

@WebServlet("/exchangeRate/*")
public class ExchangeRateServlet extends HttpServlet {

    private ExchangeRateService exchangeRateService = new ExchangeRateService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String code = req.getPathInfo().replaceFirst("/", "");
        String baseCur = code.substring(0, 3);
        String targetCur = code.substring(3);

        ExchangeRateDto exchangeRateDto = exchangeRateService.findByCodes(baseCur, targetCur);
        resp.getWriter().println(exchangeRateDto.getRate());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {


        String code = req.getParameter("currencies");
        String baseCur = code.substring(0, 3);
        String targetCur = code.substring(3);
        BigDecimal newRateValue = BigDecimal.valueOf(Long.parseLong(req.getParameter("rate")));
        ExchangeRateDto newRate = exchangeRateService.findByCodes(baseCur, targetCur);
        ExchangeRate exchangeRate = exchangeRateService.convertToEntity(newRate);
        exchangeRate.setRate(newRateValue);
        exchangeRateService.updateRate(exchangeRate);


        resp.sendRedirect(req.getContextPath() + "/exchangeRates");


    }
}

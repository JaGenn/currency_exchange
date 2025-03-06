package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Utils.Converter;
import org.example.entity.ExchangeRate;
import org.example.exception.NotFoundException;
import org.example.repository.ExchangeRateRepository;
import org.example.repository.ExchangeRateRepositoryImpl;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/exchangeRate/*")
public class ExchangeRateServlet extends HttpServlet {

    private final ExchangeRateRepository exchangeRateRepository = new ExchangeRateRepositoryImpl();
    private final Converter converter = new Converter();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String code = req.getPathInfo().replaceFirst("/", "");
        String baseCur = code.substring(0, 3);
        String targetCur = code.substring(3);

        ExchangeRate exchangeRate = exchangeRateRepository.findByCodes(baseCur, targetCur)
                .orElseThrow(() -> new NotFoundException("There is no Exchange rate with " + baseCur + targetCur + " codes"));

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        objectMapper.writeValue(resp.getWriter(), exchangeRate);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String code = req.getParameter("currencies");
        String baseCur = code.substring(0, 3);
        String targetCur = code.substring(3);
        BigDecimal newRateValue = new BigDecimal(req.getParameter("rate").replace(",","."));

        ExchangeRate exchangeRate = exchangeRateRepository.findByCodes(baseCur, targetCur)
                .orElseThrow(() -> new NotFoundException("There is no Exchange rate with " + baseCur + targetCur + " codes"));
        exchangeRate.setRate(newRateValue);
        exchangeRateRepository.updateRate(exchangeRate);

        resp.sendRedirect(req.getContextPath() + "/exchangeRates");

    }
}

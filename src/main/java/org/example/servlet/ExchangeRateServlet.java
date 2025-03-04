package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Converter;
import org.example.dto.ExchangeRateDto;
import org.example.entity.ExchangeRate;
import org.example.exception.NotFoundException;
import org.example.service.ExchangeRateService;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.spec.ECField;
import java.util.Optional;

@WebServlet("/exchangeRate/*")
public class ExchangeRateServlet extends HttpServlet {

    private final ExchangeRateService exchangeRateService = new ExchangeRateService();
    private final Converter converter = new Converter();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String code = req.getPathInfo().replaceFirst("/", "");
        String baseCur = code.substring(0, 3);
        String targetCur = code.substring(3);

        ExchangeRate exchangeRate = exchangeRateService.findByCodes(baseCur, targetCur)
                .orElseThrow(() -> new NotFoundException("There is no Exchange rate with " + baseCur + targetCur + " codes"));
        ExchangeRateDto exchangeRateDto = converter.convertToDto(exchangeRate);
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

        ExchangeRate exchangeRate = exchangeRateService.findByCodes(baseCur, targetCur)
                .orElseThrow(() -> new NotFoundException("There is no Exchange rate with " + baseCur + targetCur + " codes"));
        exchangeRate.setRate(newRateValue);
        exchangeRateService.updateRate(exchangeRate);

        resp.sendRedirect(req.getContextPath() + "/exchangeRates");

    }
}

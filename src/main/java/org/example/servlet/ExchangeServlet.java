package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Utils.Converter;
import org.example.Utils.Validator;
import org.example.dto.ExchangeRequestDto;
import org.example.exception.InvalidParameterException;
import org.example.service.ExchangeService;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/exchange")
public class ExchangeServlet extends HttpServlet {

    private final ExchangeService exchangeService = new ExchangeService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String from = req.getParameter("from");
        String to = req.getParameter("to");
        BigDecimal amount = Converter.convertToNumber(req.getParameter("amount"));


        ExchangeRequestDto exchangeRequestDto = new ExchangeRequestDto(from, to, amount);
        Validator.validate(exchangeRequestDto);

        BigDecimal result = exchangeService.exchange(from, to, amount);
        req.setAttribute("result", result);

        // Сохраняем введенные данные, чтобы они остались в форме после перезагрузки страницы
        req.setAttribute("fromValue", from);
        req.setAttribute("toValue", to);
        req.setAttribute("amountValue", amount);
        req.getRequestDispatcher("/exchangeRates").forward(req, resp);

    }


}

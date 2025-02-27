package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.dto.ExchangeRateDto;
import org.example.service.ExchangeRateService;

import java.io.IOException;
import java.util.Set;

@WebServlet("/exchanges")
public class ExchangeRatesServlet extends HttpServlet {

    private static final ExchangeRateService exchangeRateService = new ExchangeRateService();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<ExchangeRateDto> exchangeRates = exchangeRateService.selectAll();
        req.setAttribute("exList", exchangeRates);
        req.getServletContext().getRequestDispatcher("/exchange.jsp").forward(req, resp);
    }


}

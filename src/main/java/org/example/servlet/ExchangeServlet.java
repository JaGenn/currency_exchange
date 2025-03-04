package org.example.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/exchange")
public class ExchangeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String from = req.getParameter("from");
        String to = req.getParameter("to");
        double amount = Double.parseDouble(req.getParameter("amount"));
        System.out.println(req.getParameter("from"));
        System.out.println(req.getParameter("to"));
        System.out.println(req.getParameter("amount"));
        int result = 1124;
        req.setAttribute("result", result);

        // Сохраняем введенные данные, чтобы они остались в форме
        req.setAttribute("fromValue", from);
        req.setAttribute("toValue", to);
        req.setAttribute("amountValue", amount);
        req.getRequestDispatcher("/exchangeRates").forward(req, resp);

    }
}

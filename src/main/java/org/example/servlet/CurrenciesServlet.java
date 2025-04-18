package org.example.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.Utils.Validator;
import org.example.entity.CurrencyEntity;
import org.example.repository.CurrencyRepository;
import org.example.repository.CurrencyRepositoryImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/currencies")
public class CurrenciesServlet extends HttpServlet {

    private final CurrencyRepository currencyRepository = new CurrencyRepositoryImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CurrencyEntity> currencies = currencyRepository.selectAll();
        req.setAttribute("curList", currencies);
        req.getServletContext().getRequestDispatcher("/currency.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String code = req.getParameter("code");
        String fullName = req.getParameter("fullName");
        String sign = req.getParameter("sign");
        CurrencyEntity currency = new CurrencyEntity(code, fullName, sign);

        Validator.validate(currency);

        currencyRepository.save(currency);
        resp.sendRedirect(req.getContextPath() + "/currencies");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        currencyRepository.delete(Long.parseLong(id));
        resp.sendRedirect(req.getContextPath() + "/currencies");
    }
}

package by.it_academy.jd2.Mk_JD2_98_23.controllers.web;

import by.it_academy.jd2.Mk_JD2_98_23.service.api.ICurrencyService;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.IRateService;
import by.it_academy.jd2.Mk_JD2_98_23.service.factory.CurrencyServiceFactory;
import by.it_academy.jd2.Mk_JD2_98_23.service.factory.ObjectMapperFactory;
import by.it_academy.jd2.Mk_JD2_98_23.service.factory.RateServiceFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;



@WebServlet(urlPatterns = "/average-currency")
public class AverageCurrency extends HttpServlet {
    private static final String CURRENCY = "сur_abbreviation";
    private static final String MONTH = "month";
    private static final String YEAR = "year";
    private final IRateService rateService;
    private final ICurrencyService currencyService;
    private final ObjectMapper objectMapper;

    public AverageCurrency() {
        this.rateService = RateServiceFactory.getInstance();
        this.currencyService = CurrencyServiceFactory.getInstance();
        this.objectMapper = ObjectMapperFactory.getInstance();
        this.objectMapper.findAndRegisterModules();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String currency = req.getParameter(CURRENCY).toUpperCase();
        String month = req.getParameter(MONTH);
        String year = req.getParameter(YEAR);
        PrintWriter writer = resp.getWriter();

        if (currencyService.currencyValidate(currency) && (year.matches("202[2-3]") || year.matches("")) && rateService.monthValidate(month)) {
            double result = rateService.getAverageCurrency(currency, year, month);
            writer.write("Средний курс " + currency + " за месяц равен - " + result);
        } else {
            writer.write("Нет такой валюты или неправильный год или месяц!");
        }
    }
}
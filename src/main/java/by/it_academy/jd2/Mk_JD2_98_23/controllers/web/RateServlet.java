package by.it_academy.jd2.Mk_JD2_98_23.controllers.web;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RatePeriodDTO;
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
import java.time.LocalDate;
import java.util.List;

@WebServlet(urlPatterns = "/api/rate-period")
public class RateServlet extends HttpServlet {
    private static final String CURRENCY = "сur_abbreviation";
    private static final String START_DATE = "dateFrom";
    private static final String END_DATE = "dateTo";
    private final IRateService rateService;
    private final ICurrencyService currencyService;
    private final ObjectMapper objectMapper;

    public RateServlet() {
        this.rateService = RateServiceFactory.getInstance();
        this.currencyService = CurrencyServiceFactory.getInstance();
        this.objectMapper = ObjectMapperFactory.getInstance();
        this.objectMapper.findAndRegisterModules();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String currency = req.getParameter(CURRENCY).toUpperCase();
        String startDate = req.getParameter(START_DATE);
        String endDate = req.getParameter(END_DATE);
        PrintWriter writer = resp.getWriter();

        int cur = currencyService.getCurID(currency);

        try {
            if (currencyService.currencyValidate(currency)) {

                List<RateDTO> rateDTOS = rateService.checkAndLoadDataFromApi(cur,new RatePeriodDTO(currency,LocalDate.parse(startDate),LocalDate.parse(endDate)));

                writer.write(objectMapper.writeValueAsString(rateDTOS));

            } else {
                throw new ServletException("Некорректная дата или код валюты! Введите дату в формате yyyy-mm-dd, " +
                        "с 2022-12-01 до 2023-05-31. Код валюты например USD.");
            }
        } catch (Exception e) {
            writer.write(e.getMessage());
        }
    }
}

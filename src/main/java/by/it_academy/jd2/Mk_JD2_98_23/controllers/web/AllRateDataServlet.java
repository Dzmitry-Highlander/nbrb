package by.it_academy.jd2.Mk_JD2_98_23.controllers.web;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.IRateService;
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
import java.util.List;
import java.util.Objects;


@WebServlet(urlPatterns = "/all-rate")
public class AllRateDataServlet extends HttpServlet {
    private static final String CURRENCY = "сur_abbreviation";

    private final IRateService rateService;
    private final ObjectMapper objectMapper;

    public AllRateDataServlet() {
        this.rateService = RateServiceFactory.getInstance();
        this.objectMapper = ObjectMapperFactory.getInstance();
        this.objectMapper.findAndRegisterModules();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");

        String currency = req.getParameter(CURRENCY);
        PrintWriter writer = resp.getWriter();

        if (!Objects.equals(currency, "" )) {
            List<RateDTO> rateDTOList =  rateService.get(currency);
            writer.write(objectMapper.writeValueAsString(rateDTOList));
        }
    }
}
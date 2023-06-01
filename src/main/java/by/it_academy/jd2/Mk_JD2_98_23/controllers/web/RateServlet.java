package by.it_academy.jd2.Mk_JD2_98_23.controllers.web;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.dao.api.IRateDao;
import by.it_academy.jd2.Mk_JD2_98_23.dao.db.factory.RateDaoFactory;
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
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@WebServlet(urlPatterns = "/save")
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
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");

        String currency = req.getParameter(CURRENCY);
        String startDate = req.getParameter(START_DATE);
        String endDate = req.getParameter(END_DATE);
        PrintWriter writer = resp.getWriter();

        try {
            if (rateService.dateValidate(startDate) && rateService.dateValidate(endDate)) {
                int cur = currencyService.getCurID(currency);
                LocalDate start = LocalDate.parse(startDate);
                LocalDate end = LocalDate.parse(endDate);

                String url = "https://api.nbrb.by/exrates/rates/dynamics/" + cur + "?startdate=" + start
                        + "&enddate=" + end;
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                con.setRequestMethod("GET");
                con.setRequestProperty("User-Agent", "Mozilla/5.0");

                List<RateCreateDTO> rateCreateDTOS = objectMapper.readValue(con.getInputStream(),
                        objectMapper.getTypeFactory().constructCollectionType(List.class, RateCreateDTO.class));

                for (RateCreateDTO rateCreateDTO : rateCreateDTOS) {
                    if (rateService.checkRateData(rateCreateDTO)) {
                        rateService.upload(rateCreateDTO);
                    }
                }

                writer.write(rateCreateDTOS.toString());
            } else {
                throw new ServletException("Некорректная дата! Введите дату в формате yyyy-mm-dd, с 2022-12-01 до " +
                        "2023-05-31");
            }
        } catch (Exception e) {
            writer.write(e.getMessage());
        }
    }
}

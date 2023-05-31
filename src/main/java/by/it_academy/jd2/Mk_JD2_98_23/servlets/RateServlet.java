package by.it_academy.jd2.Mk_JD2_98_23.servlets;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.dao.api.IRateDao;
import by.it_academy.jd2.Mk_JD2_98_23.dao.db.factory.RateDaoFactory;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.IRateService;
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
    private final ObjectMapper objectMapper;

    public RateServlet() {
        this.rateService = RateServiceFactory.getInstance();
        this.objectMapper = new ObjectMapper();
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

        if (!Objects.equals(currency, "" ) && !Objects.equals(startDate, "") && !Objects.equals(endDate, "")) {
            LocalDate start = LocalDate.parse(startDate);
            LocalDate end = LocalDate.parse(endDate);
            String url = "https://api.nbrb.by/exrates/rates/dynamics/456?startdate=" + start + "&enddate=" + end;
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");

            List<RateCreateDTO> rateCreateDTOS = objectMapper.readValue(con.getInputStream(),
                    objectMapper.getTypeFactory().constructCollectionType(List.class, RateCreateDTO.class));

            for (RateCreateDTO rateCreateDTO : rateCreateDTOS) {
                rateService.upload(rateCreateDTO);
            }
        }
    }
}

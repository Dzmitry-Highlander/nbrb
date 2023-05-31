package by.it_academy.jd2.Mk_JD2_98_23.controllers.web;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateCreateDTO;
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
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


@WebServlet(urlPatterns = "/rate")
public class RateServlet extends HttpServlet {
    private static final String CURRENCY = "Cur_Abbreviation";
    private static final String DATE_FROM = "dateFrom";
    private static final String DATE_TO = "dateTo";
    private final IRateService rateService;
    private final ObjectMapper objectMapper;

    public RateServlet() {
        this.rateService = RateServiceFactory.getInstance();
        this.objectMapper = ObjectMapperFactory.getInstance();
        this.objectMapper.findAndRegisterModules();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");

        String currency = req.getParameter(CURRENCY);
        String dateFrom = req.getParameter(DATE_FROM);
        String dateTo = req.getParameter(DATE_TO);

        PrintWriter writer = resp.getWriter();

        if (!Objects.equals(currency, "") && !Objects.equals(dateFrom, "") && !Objects.equals(dateTo, "")) {
            LocalDate from = LocalDate.parse(dateFrom);
            LocalDate to = LocalDate.parse(dateTo);
            String url = "https://api.nbrb.by/exrates/rates/dynamics/" + currency + "?startdate=" + dateFrom + "&enddate=" + dateTo;

            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");


            try {
                List<RateCreateDTO> list = this.objectMapper.readerForListOf(RateCreateDTO.class).readValue(con.getInputStream());
                writer.write(list.size() + " count");
                for (RateCreateDTO dto : list) {
                    this.rateService.save(dto);
                }
            } catch (Exception e) {
                e.printStackTrace();
                writer.write("Error: " + e.getMessage());
            }
        }
    }
}
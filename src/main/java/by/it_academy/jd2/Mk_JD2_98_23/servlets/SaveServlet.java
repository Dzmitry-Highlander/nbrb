package by.it_academy.jd2.Mk_JD2_98_23.servlets;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.ISaveService;
import by.it_academy.jd2.Mk_JD2_98_23.service.factory.SaveServiceFactory;
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
import java.util.Objects;


@WebServlet(urlPatterns = "/save")
public class SaveServlet extends HttpServlet {
    private static final String CURRENCY = "Cur_Abbreviation";
    private static final String DATE_FROM = "dateFrom";
    private static final String DATE_TO = "dateTo";
    private final ISaveService saveService;
    private final ObjectMapper objectMapper;

    public SaveServlet() {
        this.saveService = SaveServiceFactory.getInstance();
        this.objectMapper = new ObjectMapper();
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

        if (!Objects.equals(currency, "" ) && !Objects.equals(dateFrom, "") && !Objects.equals(dateTo, "")) {
            LocalDate from = LocalDate.parse(dateFrom);
            LocalDate to = LocalDate.parse(dateTo);
            String url = "https://api.nbrb.by/exrates/rates/" + currency + "?parammode=2&ondate=";

            while (from.isBefore(to.plusDays(1))) {
                URL obj = new URL(url + from);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                con.setRequestMethod("GET");
                con.setRequestProperty("User-Agent", "Mozilla/5.0");

                RateDTO dto = this.objectMapper.readValue(con.getInputStream(), RateDTO.class);
                this.saveService.save(dto);

                from = from.plusDays(1);

                writer.write(dto.toString());
            }
        }
    }
}

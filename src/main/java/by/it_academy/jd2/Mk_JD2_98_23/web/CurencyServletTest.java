package by.it_academy.jd2.Mk_JD2_98_23.web;


import by.it_academy.jd2.Mk_JD2_98_23.core.dto.CurrencyCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.ICurrencyService;
import by.it_academy.jd2.Mk_JD2_98_23.service.factory.CurrencyServiceFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


@WebServlet(urlPatterns = "/test")
public class CurencyServletTest extends HttpServlet {

    private final ICurrencyService currencyService;
    private final ObjectMapper objectMapper;

    public CurencyServletTest() {
        this.currencyService = CurrencyServiceFactory.getInstance();
        this.objectMapper = new ObjectMapper();
        this.objectMapper.findAndRegisterModules();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");

        String url = "https://api.nbrb.by/exrates/currencies";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        List<CurrencyCreateDTO> list = this.objectMapper.readValue(con.getInputStream(), new TypeReference<List<CurrencyCreateDTO>>() {
        });

        for (CurrencyCreateDTO dto : list) {
            this.currencyService.save(dto);
        }

        resp.getWriter().write("All currencies have been added to the database.");
    }
}
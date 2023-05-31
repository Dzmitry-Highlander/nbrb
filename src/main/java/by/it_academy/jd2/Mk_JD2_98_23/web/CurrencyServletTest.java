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
public class CurrencyServletTest extends HttpServlet {

    private final ICurrencyService currencyService;
    private final ObjectMapper objectMapper;

    public CurrencyServletTest() {
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

        List<CurrencyCreateDTO> list = this.objectMapper.readValue(con.getInputStream(), new TypeReference<>() {
        });

        for (CurrencyCreateDTO dto : list) {
            this.currencyService.uploadData(dto);
        }

        // test info block
        resp.getWriter().write("All currencies have been added to the database.");
        resp.getWriter().write(list.size() + " - размер листа. Тест. " + list.get(1));

        for (CurrencyCreateDTO currencyCreateDTO : list) {
            resp.getWriter().write(currencyCreateDTO.getCurName() + "\n");

        }
    }
}
package by.it_academy.jd2.Mk_JD2_98_23.controllers.web;


import by.it_academy.jd2.Mk_JD2_98_23.core.dto.CurrencyDTO;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.ICurrencyService;
import by.it_academy.jd2.Mk_JD2_98_23.service.factory.CurrencyServiceFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
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

        List<CurrencyDTO> list = currencyService.get();

        for (CurrencyDTO currencyCreateDTO : list) {
            resp.getWriter().write(currencyCreateDTO.getCurName() + "\n");
        }

        resp.getWriter().write(currencyService.get(456).getCurName() + "\n" + "TEEEEST ONLY ID");
    }
}
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
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");

        String currency = req.getParameter(CURRENCY);
        String dateFrom = req.getParameter(DATE_FROM);
        String dateTo = req.getParameter(DATE_TO);

        PrintWriter writer = resp.getWriter();

        if (!Objects.equals(currency, "") && !Objects.equals(dateFrom, "") && !Objects.equals(dateTo, "")) {

        }
    }
}

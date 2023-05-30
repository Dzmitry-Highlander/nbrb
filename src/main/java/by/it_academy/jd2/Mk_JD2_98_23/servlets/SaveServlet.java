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
import java.util.List;

@WebServlet(urlPatterns = "/save")
public class SaveServlet extends HttpServlet {
    private final ISaveService saveService;
    private final ObjectMapper objectMapper;

    public SaveServlet() {
        this.saveService = SaveServiceFactory.getInstance();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json; charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        List<RateDTO> rateDTOS = this.saveService.get();

        writer.write(objectMapper.writeValueAsString(rateDTOS));
    }
}

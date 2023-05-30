package by.it_academy.jd2.Mk_JD2_98_23.servlets;

import by.it_academy.jd2.Mk_JD2_98_23.service.api.ISaveService;
import by.it_academy.jd2.Mk_JD2_98_23.service.factory.SaveServiceFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(urlPatterns = "/save")
public class SaveServlet extends HttpServlet {
    private final ISaveService saveService;
    private ObjectMapper objectMapper;

    public SaveServlet() {
        this.saveService = SaveServiceFactory.getInstance();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}

package by.it_academy.jd2.Mk_JD2_98_23.controllers.listiners;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.CurrencyCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.dao.db.ds.DatabaseConnectionFactory;
import by.it_academy.jd2.Mk_JD2_98_23.dao.exceptions.AccessDataException;
import by.it_academy.jd2.Mk_JD2_98_23.dao.exceptions.DataInsertionErrorException;
import by.it_academy.jd2.Mk_JD2_98_23.service.api.ICurrencyService;
import by.it_academy.jd2.Mk_JD2_98_23.service.factory.CurrencyServiceFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AppServletContextListener implements ServletContextListener {

    private final ICurrencyService currencyService;
    private final ObjectMapper objectMapper;

    public AppServletContextListener() {
        this.currencyService = CurrencyServiceFactory.getInstance();
        this.objectMapper = new ObjectMapper();
        this.objectMapper.findAndRegisterModules();
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             Statement st = conn.createStatement()) {
            ResultSet rs = conn.getMetaData().getTables("", "app", "WEEKENDS", null);
            if (!rs.next()) {
                String sqlScript = new String(AppServletContextListener.class.getClassLoader().getResourceAsStream("data.sql").readAllBytes());
                st.executeUpdate(sqlScript);
            }

        } catch (SQLException | IOException e) {
            throw new AccessDataException("Ошибка загрузки данных из data.sql", e);
        }

        int count = this.currencyService.getCount();
        if (count == 0) {
            try {
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

            } catch (IOException e) {
                throw new DataInsertionErrorException("Ошибка загрузки валют из API", e);
            }
        }
    }
}
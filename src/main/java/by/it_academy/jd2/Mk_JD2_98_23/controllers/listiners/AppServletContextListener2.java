package by.it_academy.jd2.Mk_JD2_98_23.controllers.listiners;

import by.it_academy.jd2.Mk_JD2_98_23.dao.db.ds.DatabaseConnectionFactory;
import by.it_academy.jd2.Mk_JD2_98_23.dao.exceptions.AccessDataException;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AppServletContextListener2 implements ServletContextListener {


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             Statement st = conn.createStatement()) {
            ResultSet rs = conn.getMetaData().getTables("", "app", "WEEKENDS", null);
            if (!rs.next()) {
                String sqlScript = new String(AppServletContextListener2.class.getClassLoader().getResourceAsStream("data.sql").readAllBytes());
                st.executeUpdate(sqlScript);
            }

        } catch (SQLException | IOException e) {
            throw new AccessDataException("Ошибка загрузки данных из data.sql", e);
        }
    }
}
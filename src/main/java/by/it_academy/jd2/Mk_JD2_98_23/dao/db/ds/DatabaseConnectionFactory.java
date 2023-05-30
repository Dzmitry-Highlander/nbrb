package by.it_academy.jd2.Mk_JD2_98_23.dao.db.ds;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionFactory {
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Ошибка, драйвер для базы не найден", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://192.168.1.100:5432/rb-nb";
        Properties props = new Properties();
        props.setProperty("user", "nbrb");
        props.setProperty("password", "nbrb2023");

        return DriverManager.getConnection(url, props);
    }
}

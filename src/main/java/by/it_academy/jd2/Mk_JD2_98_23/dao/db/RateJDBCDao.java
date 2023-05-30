package by.it_academy.jd2.Mk_JD2_98_23.dao.db;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.dao.api.IRateDao;
import by.it_academy.jd2.Mk_JD2_98_23.dao.db.ds.DatabaseConnectionFactory;
import by.it_academy.jd2.Mk_JD2_98_23.exceptions.AccessDataException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RateJDBCDao implements IRateDao {
    @Override
    public List<RateDTO> get() {
        List<RateDTO> data = new ArrayList<>();

        try (Connection conn = DatabaseConnectionFactory.getConnection();
             Statement st = conn.createStatement();
             //TODO исправить SQL запрос
             ResultSet rs = st.executeQuery("SELECT id, name FROM app.artists ORDER BY id ASC")) {

            while (rs.next()) {
                RateDTO dto = new RateDTO();
                //TODO исправить создание DTO
                dto.setId(rs.getInt("id"));
                data.add(dto);
            }
        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }

        return data;
    }

    @Override
    public RateDTO get(int id) {
        return null;
    }

    @Override
    public RateDTO save(RateDTO item) {
        return null;
    }
}

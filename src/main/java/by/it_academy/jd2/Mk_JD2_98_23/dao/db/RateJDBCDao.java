package by.it_academy.jd2.Mk_JD2_98_23.dao.db;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateCreateDTO;
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
    public List<RateCreateDTO> get() {
        List<RateCreateDTO> data = new ArrayList<>();

        try (Connection conn = DatabaseConnectionFactory.getConnection();
             Statement st = conn.createStatement();
             //TODO исправить SQL запрос
             ResultSet rs = st.executeQuery("SELECT id, name FROM app.artists ORDER BY id ASC")) {

            while (rs.next()) {
                RateCreateDTO dto = new RateCreateDTO();
                dto.setCurName(rs.getString("id"));
                //TODO исправить создание DTO
                data.add(dto);
            }
        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }

        return data;
    }

    @Override
    public RateCreateDTO get(int id) {
        RateCreateDTO dto = null;
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             Statement st = conn.createStatement();
             //TODO исправить SQL запрос
             ResultSet rs = st.executeQuery("SELECT id, name FROM app.artists WHERE id = " +
                     id + " ORDER BY id ASC")) {

            if (rs.next()) {
                dto = new RateCreateDTO();
                dto.setCurName(rs.getString("id"));
                //TODO исправить создание DTO
            }
        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }

        return dto;
    }

    @Override
    public RateCreateDTO save(RateCreateDTO item) {
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             Statement st = conn.createStatement();
             //TODO исправить SQL запрос
             ResultSet rs = st.executeQuery("INSERT INTO app.artists(name) VALUES " +
                     "('" + item.getCurID() + "') RETURNING id;")) {

            while (rs.next()) {
                item.setCurName(rs.getString("id"));
            }

        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }

        return item;
    }
}

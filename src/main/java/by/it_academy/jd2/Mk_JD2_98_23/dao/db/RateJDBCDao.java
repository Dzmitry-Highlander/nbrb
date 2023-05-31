package by.it_academy.jd2.Mk_JD2_98_23.dao.db;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.dao.api.IRateDao;
import by.it_academy.jd2.Mk_JD2_98_23.dao.db.ds.DatabaseConnectionFactory;
import by.it_academy.jd2.Mk_JD2_98_23.dao.exceptions.AccessDataException;
import by.it_academy.jd2.Mk_JD2_98_23.dao.exceptions.DataInsertionErrorException;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class RateJDBCDao implements IRateDao {
    @Override
    public List<RateDTO> get() {
        return null;
    }

    @Override
    public RateDTO get(int id) {
        return null;
    }

    @Override
    public boolean checkPeriod(LocalDate from, LocalDate to) {
        return false;
    }

    @Override
    public void save(RateCreateDTO item) {
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement st = conn.prepareStatement("INSERT INTO app.rate(cur_id, date, cur_officialrate) VALUES (?, ?, ?)  RETURNING id;")) {

            st.setInt(1, item.getCurId());
            st.setTimestamp(2, Timestamp.valueOf(item.getDate()));
            st.setDouble(3, item.getCurOfficialRate());

            int rowsInserted = st.executeUpdate();
            if (rowsInserted == 0) {
                throw new DataInsertionErrorException("Ошибка вставки данных: ни одна строка не была добавлена в таблицу.");
            }
        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }
    }
}

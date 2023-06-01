package by.it_academy.jd2.Mk_JD2_98_23.dao.db;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.dao.api.IRateDao;
import by.it_academy.jd2.Mk_JD2_98_23.dao.db.ds.DatabaseConnectionFactory;
import by.it_academy.jd2.Mk_JD2_98_23.dao.exceptions.AccessDataException;
import by.it_academy.jd2.Mk_JD2_98_23.dao.exceptions.DataInsertionErrorException;

import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class RateJDBCDao implements IRateDao {
    @Override
    public List<RateCreateDTO> get() {
        List<RateCreateDTO> data = new ArrayList<>();

        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT cur_id, cur_date, cur_official_rate FROM " +
                     "app.rate ORDER BY cur_id ASC")) {
             ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                RateCreateDTO dto = new RateCreateDTO();
                dto.setCurID(rs.getInt("cur_id"));
                dto.setDate(rs.getDate("cur_date").toLocalDate().atStartOfDay());
                dto.setCurOfficialRate(rs.getDouble("cur_official_rate"));

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
             PreparedStatement st = conn
                     .prepareStatement("SELECT cur_id, cur_date, cur_official_rate FROM " +
                             "app.rate WHERE cur_id = ? ORDER BY cur_id ASC")) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                dto = new RateCreateDTO();
                dto.setCurID(rs.getInt("cur_id"));
                dto.setDate(rs.getDate("cur_date").toLocalDate().atStartOfDay());
                dto.setCurOfficialRate(rs.getDouble("cur_official_rate"));
            }
        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }

        return dto;
    }

    @Override
    public void save(RateCreateDTO item) {
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO app.rate(cur_id, cur_date, " +
                     "cur_official_rate) VALUES (?, ?, ?);")) {
            ps.setObject(1, item.getCurID());
            ps.setObject(2, item.getDate());
            ps.setObject(3, item.getCurOfficialRate());

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted == 0) {
                throw new DataInsertionErrorException("Ошибка вставки данных: ни одна строка не была добавлена " +
                        "в таблицу.");
            }

        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }
    }

    @Override
    public boolean checkRateDataPeriod(String curAbbreviation, LocalDate dateStart, LocalDate dateEnd) {
        boolean result = false;
        long dateBetween = ChronoUnit.DAYS.between(dateStart, dateEnd) + 1;

        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement st = conn
                     .prepareStatement("SELECT COUNT(*) FROM ( " +
                             "    SELECT cur_id, DATE(cur_date) AS hoho, COUNT(cur_abbreviation) " +
                             "    FROM app.rate " +
                             "    JOIN app.currency USING (cur_id) " +
                             "    WHERE DATE(cur_date) BETWEEN DATE('"+ dateStart + "') " +
                             "      AND DATE('"+ dateEnd +"') " +
                             "      AND cur_abbreviation = '" + curAbbreviation + "' " +
                             "    GROUP BY cur_id, hoho " +
                             ") AS sub;")) {
            ResultSet rs = st.executeQuery();

            if (rs.next() && dateBetween == rs.getLong(1)) {
                result = true;
            }
        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }

        return result;
    }

    @Override
    public boolean checkRateData(RateCreateDTO item) {
        boolean result = true;
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement st = conn
                     .prepareStatement("SELECT cur_id, cur_date FROM " +
                             "app.rate WHERE cur_id = " + item.getCurID() +" AND cur_date = '" +
                             item.getDate() + "' ORDER BY cur_id ASC")) {
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                result = false;
            }
        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }

        return result;
    }

    @Override
    public boolean currencyValidate(String item) {
        boolean result = false;
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement st = conn
                     .prepareStatement("SELECT cur_abbreviation FROM app.currency WHERE cur_abbreviation = " +
                             "'" + item.toUpperCase() + "';")) {
            ResultSet rs = st.executeQuery();

            if (rs.getString(1).matches("\\.{3}")) {
                result = true;
            }
        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }

        return result;
    }
}

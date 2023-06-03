package by.it_academy.jd2.Mk_JD2_98_23.dao.db;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.RateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.dao.api.IRateDao;
import by.it_academy.jd2.Mk_JD2_98_23.dao.db.ds.DatabaseConnectionFactory;
import by.it_academy.jd2.Mk_JD2_98_23.dao.exceptions.AccessDataException;
import by.it_academy.jd2.Mk_JD2_98_23.dao.exceptions.DataInsertionErrorException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
             PreparedStatement ps = conn
                     .prepareStatement("SELECT cur_id, cur_date, cur_official_rate FROM " +
                             "app.rate WHERE cur_id = ? ORDER BY cur_id ASC")) {

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

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
    public List<RateDTO> get(String curAbbreviation) {
        List<RateDTO> data = new ArrayList<>();

        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT DATE(cur_date) as cur_date, cur_abbreviation, cur_official_rate " +
                     "FROM app.rate " +
                     "JOIN app.currency USING (cur_id) " +
                     "WHERE cur_abbreviation  = ? " +
                     "ORDER BY cur_date;")) {

            ps.setString(1, curAbbreviation);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                RateDTO dto = new RateDTO();
                dto.setDate(rs.getDate("cur_date").toLocalDate());
                dto.setCurAbbreviation(rs.getString("cur_abbreviation"));
                dto.setCurOfficialRate(rs.getDouble("cur_official_rate"));

                data.add(dto);
            }
        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }

        return data;
    }

    @Override
    public void save(RateCreateDTO item) {
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO app.rate(cur_id, cur_date, " +
                     "cur_official_rate) VALUES (?, ?, ?);")) {

            ps.setInt(1, item.getCurID());
            ps.setObject(2, item.getDate());
            ps.setDouble(3, item.getCurOfficialRate());

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
             PreparedStatement ps = conn
                     .prepareStatement("SELECT COUNT(*) FROM ( " +
                             "    SELECT cur_id, DATE(cur_date) AS hoho, COUNT(cur_abbreviation) " +
                             "    FROM app.rate " +
                             "    JOIN app.currency USING (cur_id) " +
                             "    WHERE DATE(cur_date) BETWEEN DATE(?) " +
                             "      AND DATE(?) " +
                             "      AND cur_abbreviation = ? " +
                             "    GROUP BY cur_id, hoho " +
                             ") AS sub;")) {

            ps.setObject(1, dateStart);
            ps.setObject(2, dateEnd);
            ps.setString(3, curAbbreviation);


            ResultSet rs = ps.executeQuery();

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
             PreparedStatement ps = conn
                     .prepareStatement("SELECT cur_id, cur_date FROM " +
                             "app.rate WHERE cur_id = ? AND cur_date = ? ORDER BY cur_id ASC")) {

            ps.setInt(1, item.getCurID());
            ps.setObject(2, item.getDate());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = false;
            }
        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }

        return result;
    }

    @Override
    public double getAverageCurrency(LocalDate date, String curAbbreviation) {
        double result;
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement ps = conn
                     .prepareStatement("SELECT ROUND(AVG(cur_official_rate)::numeric, 4)  FROM (SELECT DATE(cur_date) as cur_date, cur_abbreviation," +
                             "  cur_official_rate, calendar_date, is_day_off" +
                             "  FROM app.rate" +
                             " JOIN app.currency USING (cur_id)" +
                             " JOIN  app.weekends ON app.rate.cur_date = app.weekends.calendar_date" +
                             " WHERE cur_abbreviation  = ? AND is_day_off = 0 " +
                             " AND  EXTRACT(MONTH FROM cur_date) = EXTRACT(MONTH FROM DATE '" + date + "')" +
                             " AND EXTRACT(YEAR FROM cur_date) = EXTRACT(YEAR FROM DATE '" + date + "')" +
                             "ORDER BY cur_date) as sub;")) {

            ps.setString(1, curAbbreviation);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = rs.getDouble(1);
            } else {
                throw new DataInsertionErrorException("Ошибка получения данных: средний курс не был получен");
            }

        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }

        return result;
    }

    @Override
    public List<RateDTO> getPeriod(String curAbbreviation, LocalDate dateStart, LocalDate dateEnd) {
        List<RateDTO> data = new ArrayList<>();
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT cur_abbreviation, DATE(cur_date) AS date_cur, cur_official_rate " +
                                "FROM app.rate " +
                                   "JOIN app.currency USING (cur_id) " +
                                   "WHERE DATE(cur_date) BETWEEN DATE(?) " +
                                     "AND DATE(?) AND cur_abbreviation = ? " +
                                   "ORDER BY date_cur;")) {

            ps.setObject(1, dateStart);
            ps.setObject(2, dateEnd);
            ps.setString(3, curAbbreviation);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                RateDTO dto = new RateDTO();
                dto.setCurAbbreviation(rs.getString("cur_abbreviation"));
                dto.setDate(rs.getDate("date_cur").toLocalDate());
                dto.setCurOfficialRate(rs.getDouble("cur_official_rate"));

                data.add(dto);
            }
        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }

        return data;
    }
}

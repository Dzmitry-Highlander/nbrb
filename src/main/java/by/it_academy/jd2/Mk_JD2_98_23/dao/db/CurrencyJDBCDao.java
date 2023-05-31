package by.it_academy.jd2.Mk_JD2_98_23.dao.db;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.CurrencyCreateDTO;
import by.it_academy.jd2.Mk_JD2_98_23.core.dto.CurrencyDTO;
import by.it_academy.jd2.Mk_JD2_98_23.dao.api.ICurrencyDao;
import by.it_academy.jd2.Mk_JD2_98_23.dao.db.ds.DatabaseConnectionFactory;
import by.it_academy.jd2.Mk_JD2_98_23.dao.exceptions.AccessDataException;
import by.it_academy.jd2.Mk_JD2_98_23.dao.exceptions.DataInsertionErrorException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CurrencyJDBCDao implements ICurrencyDao {
    @Override
    public List<CurrencyDTO> get() {
        List<CurrencyDTO> data = new ArrayList<>();

        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement ps = conn
                     .prepareStatement("SELECT cur_id, cur_code, cur_abbreviation, cur_name, cur_name_bel, " +
                             "cur_name_eng, cur_quotname, cur_quotname_bel, cur_quotname_eng, cur_scale FROM " +
                             "app.currency ORDER BY cur_id ASC")) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                CurrencyDTO dto = new CurrencyDTO();
                dto.setCurID(rs.getInt("cur_id"));
                dto.setCurCode(rs.getInt("cur_code"));
                dto.setCurAbbreviation(rs.getString("cur_abbreviation"));
                dto.setCurName(rs.getString("cur_name"));
                dto.setCurNameBel(rs.getString("cur_name_bel"));
                dto.setCurNameEng(rs.getString("cur_name_eng"));
                dto.setCurQuotName(rs.getString("cur_quotname"));
                dto.setCurQuotNameBel(rs.getString("cur_quotname_bel"));
                dto.setCurQuotNameEng(rs.getString("cur_quotname_eng"));
                dto.setCurScale(rs.getInt("cur_scale"));

                data.add(dto);
            }
        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }

        return data;
    }

    @Override
    public CurrencyDTO get(int id) {
        CurrencyDTO dto = null;
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement st = conn
                     .prepareStatement("SELECT cur_id, cur_code, cur_abbreviation, cur_name, cur_name_bel, " +
                             "cur_name_eng, cur_quotname, cur_quotname_bel, cur_quotname_eng, cur_scale FROM " +
                             "app.currency WHERE cur_id = ? ORDER BY cur_id ASC")) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                dto = new CurrencyDTO();
                dto.setCurID(rs.getInt("cur_id"));
                dto.setCurCode(rs.getInt("cur_code"));
                dto.setCurAbbreviation(rs.getString("cur_abbreviation"));
                dto.setCurName(rs.getString("cur_name"));
                dto.setCurNameBel(rs.getString("cur_name_bel"));
                dto.setCurNameEng(rs.getString("cur_name_eng"));
                dto.setCurQuotName(rs.getString("cur_quotname"));
                dto.setCurQuotNameBel(rs.getString("cur_quotname_bel"));
                dto.setCurQuotNameEng(rs.getString("cur_quotname_eng"));
                dto.setCurScale(rs.getInt("cur_scale"));
            }
        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }

        return dto;
    }

    @Override
    public void uploadData(CurrencyCreateDTO item) {
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement ps = conn
                     .prepareStatement("INSERT INTO app.currency(cur_id, cur_code, cur_abbreviation, cur_name, " +
                             "cur_name_bel, cur_name_eng, cur_quotname, cur_quotname_bel, cur_quotname_eng, " +
                             "cur_namemulti, cur_name_belmulti, cur_name_engmulti, cur_scale) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
            ps.setInt(1, item.getCurID());
            ps.setInt(2, Integer.parseInt(item.getCurCode()));
            ps.setString(3, item.getCurAbbreviation());
            ps.setString(4, item.getCurName());
            ps.setString(5, item.getCurNameBel());
            ps.setString(6, item.getCurNameEng());
            ps.setString(7, item.getCurQuotName());
            ps.setString(8, item.getCurQuotNameBel());
            ps.setString(9, item.getCurQuotNameEng());
            ps.setString(10, item.getCurNameMulti());
            ps.setString(11, item.getCurNameBelMulti());
            ps.setString(12, item.getCurNameEngMulti());
            ps.setInt(13, item.getCurScale());

            int rowsInserted = ps.executeUpdate();
            if (rowsInserted == 0) {
                throw new DataInsertionErrorException("Ошибка вставки данных: ни одна строка не была добавлена в " +
                        "таблицу.");
            }
        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }
    }

    @Override
    public int getCount() {
        int count = 0;
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM app.currency;")) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new AccessDataException("Ошибка при получении количества записей в таблице app.currency", e);
        }
        return count;
    }
}

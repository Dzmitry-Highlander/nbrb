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
             PreparedStatement st = conn
                     .prepareStatement("SELECT cur_id, cur_code, cur_abbreviation, cur_name, cur_name_bel, " +
                             "cur_name_eng, cur_quotname, cur_quotname_bel, cur_quotname_eng, cur_scale FROM " +
                             "app.currency ORDER BY cur_id ASC")) {
            ResultSet rs = st.executeQuery();

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
    public CurrencyDTO save(CurrencyDTO item) {
        return null;
    }

    @Override
    public void uploadData(CurrencyCreateDTO item) {
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement st = conn
                     .prepareStatement("INSERT INTO app.currency(cur_id, cur_code, cur_abbreviation, cur_name, " +
                             "cur_name_bel, cur_name_eng, cur_quotname, cur_quotname_bel, cur_quotname_eng, " +
                             "cur_namemulti, cur_name_belmulti, cur_name_engmulti, cur_scale) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
            st.setInt(1, item.getCurID());
            st.setInt(2, Integer.parseInt(item.getCurCode()));
            st.setString(3, item.getCurAbbreviation());
            st.setString(4, item.getCurName());
            st.setString(5, item.getCurNameBel());
            st.setString(6, item.getCurNameEng());
            st.setString(7, item.getCurQuotName());
            st.setString(8, item.getCurQuotNameBel());
            st.setString(9, item.getCurQuotNameEng());
            st.setString(10, item.getCurNameMulti());
            st.setString(11, item.getCurNameBelMulti());
            st.setString(12, item.getCurNameEngMulti());
            st.setInt(13, item.getCurScale());

            int rowsInserted = st.executeUpdate();
            if (rowsInserted == 0) {
                throw new DataInsertionErrorException("Ошибка вставки данных: ни одна строка не была добавлена в таблицу.");
            }
        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }
    }

    @Override
    public int getCount() {
        int count = 0;
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement st = conn.prepareStatement("SELECT COUNT(*) as count FROM app.currency");
             ResultSet rs = st.executeQuery()) {

            if (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }
        return count;
    }
}

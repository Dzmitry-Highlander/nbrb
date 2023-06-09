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
                             "app.currency JOIN app.cur_lang USING (cur_id) ORDER BY cur_id ASC")) {
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

//    @Override
//    public void uploadData(CurrencyCreateDTO item) {
//        try (Connection conn = DatabaseConnectionFactory.getConnection();
//             PreparedStatement ps = conn
//                     .prepareStatement("INSERT INTO app.currency(cur_id, cur_code, cur_abbreviation, cur_name, " +
//                             "cur_name_bel, cur_name_eng, cur_quotname, cur_quotname_bel, cur_quotname_eng, " +
//                             "cur_namemulti, cur_name_belmulti, cur_name_engmulti, cur_scale, cur_date_start, cur_date_end) " +
//                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
//
//            ps.setInt(1, item.getCurID());
//            ps.setInt(2, Integer.parseInt(item.getCurCode()));
//            ps.setString(3, item.getCurAbbreviation());
//            ps.setString(4, item.getCurName());
//            ps.setString(5, item.getCurNameBel());
//            ps.setString(6, item.getCurNameEng());
//            ps.setString(7, item.getCurQuotName());
//            ps.setString(8, item.getCurQuotNameBel());
//            ps.setString(9, item.getCurQuotNameEng());
//            ps.setString(10, item.getCurNameMulti());
//            ps.setString(11, item.getCurNameBelMulti());
//            ps.setString(12, item.getCurNameEngMulti());
//            ps.setInt(13, item.getCurScale());
//            ps.setObject(14, item.getCurDateStart());
//            ps.setObject(15, item.getCurDateEnd());
//
//            int rowsInserted = ps.executeUpdate();
//            if (rowsInserted == 0) {
//                throw new DataInsertionErrorException("Ошибка вставки данных: ни одна строка не была добавлена в " +
//                        "таблицу.");
//            }
//        } catch (SQLException e) {
//            throw new AccessDataException("Ошибка подключения к базе данных", e);
//        }
//    }

    @Override
    public void uploadData(CurrencyCreateDTO item) {
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement psCurrency = conn
                     .prepareStatement("INSERT INTO app.currency(cur_id, cur_code, cur_abbreviation, cur_name, " +
                             " cur_scale) " +
                             "VALUES (?, ?, ?, ?, ?);");
             PreparedStatement psCurrencyLang = conn
                     .prepareStatement("INSERT INTO app.cur_lang(cur_id, cur_name_bel, cur_name_eng, cur_quotname, " +
                             "cur_quotname_bel, cur_quotname_eng, cur_namemulti, cur_name_belmulti, cur_name_engmulti) " +
                             "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);");
             PreparedStatement psCurrencyExpDate  = conn
                     .prepareStatement("INSERT INTO app.cur_expdate(cur_id, cur_date_start, cur_date_end) " +
                             "VALUES (?, ?, ?);")) {

            psCurrency.setInt(1, item.getCurID());
            psCurrency.setInt(2, Integer.parseInt(item.getCurCode()));
            psCurrency.setString(3, item.getCurAbbreviation());
            psCurrency.setString(4, item.getCurName());
            psCurrency.setInt(5, item.getCurScale());

            int currencyRowsInserted = psCurrency.executeUpdate();
            if (currencyRowsInserted == 0) {
                throw new DataInsertionErrorException("Ошибка вставки данных: ни одна строка не была добавлена в " +
                        "таблицу currency.");
            }

            psCurrencyLang.setInt(1, item.getCurID());
            psCurrencyLang.setString(2, item.getCurNameBel());
            psCurrencyLang.setString(3, item.getCurNameEng());
            psCurrencyLang.setString(4, item.getCurQuotName());
            psCurrencyLang.setString(5, item.getCurQuotNameBel());
            psCurrencyLang.setString(6, item.getCurQuotNameEng());
            psCurrencyLang.setString(7, item.getCurNameMulti());
            psCurrencyLang.setString(8, item.getCurNameBelMulti());
            psCurrencyLang.setString(9, item.getCurNameEngMulti());

            int currencyLangRowsInserted = psCurrencyLang.executeUpdate();
            if (currencyLangRowsInserted == 0) {
                throw new DataInsertionErrorException("Ошибка вставки данных: ни одна строка не была добавлена в " +
                        "таблицу cur_lang");
            }

            psCurrencyExpDate.setInt(1, item.getCurID());
            psCurrencyExpDate.setObject(2, item.getCurDateStart());
            psCurrencyExpDate.setObject(3, item.getCurDateEnd());

            int currencyExpDateRowsInserted= psCurrencyExpDate.executeUpdate();
            if (currencyExpDateRowsInserted == 0) {
                throw new DataInsertionErrorException("Ошибка вставки данных: ни одна строка не была добавлена в " +
                        "таблицу app.cur_expdate");
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

    @Override
    public int getCurID(String curAbbreviation) {
        int id = 0;
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT cur_id FROM app.currency WHERE " +
                     "cur_abbreviation = ? ORDER BY cur_id DESC LIMIT 1;")) {

            ps.setString(1, curAbbreviation);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new AccessDataException("Ошибка при получении ID валюты в таблице app.currency", e);
        }

        return id;
    }

    @Override
    public boolean currencyValidate(String curAbbreviation) {
        boolean result = false;
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement ps = conn
                     .prepareStatement("SELECT cur_abbreviation FROM app.currency WHERE cur_abbreviation = ?;")) {

            ps.setString(1, curAbbreviation);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = true;
            }
        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }

        return result;
    }
}

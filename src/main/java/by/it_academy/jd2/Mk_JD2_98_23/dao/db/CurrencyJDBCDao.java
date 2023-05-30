package by.it_academy.jd2.Mk_JD2_98_23.dao.db;

import by.it_academy.jd2.Mk_JD2_98_23.core.dto.CurrencyDTO;
import by.it_academy.jd2.Mk_JD2_98_23.dao.api.ICurrencyDao;
import by.it_academy.jd2.Mk_JD2_98_23.dao.db.ds.DatabaseConnectionFactory;
import by.it_academy.jd2.Mk_JD2_98_23.dao.exceptions.AccessDataException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CurrencyJDBCDao implements ICurrencyDao {

    private Connection connection;

    @Override
    public List<CurrencyDTO> get() {
        return null;
    }

    @Override
    public CurrencyDTO get(int id) {
        return null;
    }

    @Override
    public CurrencyDTO save(CurrencyDTO item) {
        try (Connection conn = DatabaseConnectionFactory.getConnection();
             PreparedStatement st = conn.prepareStatement("INSERT INTO app.currency(Cur_ID, Cur_Code, Cur_Abbreviation, Cur_Name," +
                     " Cur_Name_Bel, Cur_Name_Eng, Cur_QuotName, Cur_QuotName_Bel, Cur_QuotName_Eng, Cur_NameMulti, Cur_Name_BelMulti," +
                     " Cur_Name_EngMulti, Cur_Scale) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING Cur_ID;")) {

            st.setInt(1, item.getCurID());


        } catch (SQLException e) {
            throw new AccessDataException("Ошибка подключения к базе данных", e);
        }

        return item;
    }
}

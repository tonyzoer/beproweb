package com.zoer.bepro.model.dao.mysqldao;

import com.zoer.bepro.model.dao.AbstractJDBCDao;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.JobOfferText;
import com.zoer.bepro.model.domain.Profile;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zoer on 24.01.17.
 */
public class MySqlJobOfferTextDao extends AbstractJDBCDao<JobOfferText,Integer> {

    @Override
    public JobOfferText create() throws PersistException {
        return persist(new JobOfferText(){});
    }

    @Override
    public String getSelectAllQuery() {
        return "select * from jobofferinfo";
    }

    @Override
    public String getSelectQuery() {
        return "select * from jobofferinfo where idjobofferinfo=?";
    }

    @Override
    public String getCreateQuery() {
        return "";
    }

    @Override
    public String getUpdateQuery() {
        return "update jobofferinfo set fulltextvalue=? where idjobofferinfo=?";
    }

    @Override
    public String getDeleteQuery() {
        return "";
    }

    @Override
    public String getSelectLastInsertQuery() {
        return "select * from jobofferinfo where idjobofferinfo=last_insert_id()";
    }

    @Override
    protected List<JobOfferText> parseResultSet(ResultSet rs) throws PersistException {
        List<JobOfferText> result = new LinkedList<>();
        try {
            while (rs.next()) {
                JobOfferText jot=new JobOfferText();
                jot.setId(rs.getInt("idjobofferinfo"));
                jot.setText(rs.getString("fulltextvalue"));
                result.add(jot);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, JobOfferText object) throws PersistException {
    //NO NEED
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, JobOfferText object) throws PersistException {
        try {
            statement.setString(1,object.getText());
            statement.setInt(2,object.getId());
        } catch (SQLException e) {
            throw new PersistException(e);
        }
    }

}

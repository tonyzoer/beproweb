package com.zoer.bepro.model.dao.mysqldao;

import com.zoer.bepro.model.dao.AbstractJDBCDao;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.AdminProfile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by zoer on 31.12.16.
 */
public class MySqlAdminProfile extends AbstractJDBCDao<AdminProfile,Integer> {


    @Override
    public AdminProfile create() throws PersistException {
        return null;
    }

    @Override
    public String getSelectAllQuery() {
        return null;
    }

    @Override
    public String getSelectQuery() {
        return null;
    }

    @Override
    public String getCreateQuery() {
        return null;
    }

    @Override
    public String getUpdateQuery() {
        return null;
    }

    @Override
    public String getDeleteQuery() {
        return null;
    }

    @Override
    public String getSelectLastInsertQuery() {
        return null;
    }

    @Override
    protected List<AdminProfile> parseResultSet(ResultSet rs) throws PersistException {
        return null;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, AdminProfile object) throws PersistException {

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, AdminProfile object) throws PersistException {

    }
}

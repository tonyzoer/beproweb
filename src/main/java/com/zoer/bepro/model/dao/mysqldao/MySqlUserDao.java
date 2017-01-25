package com.zoer.bepro.model.dao.mysqldao;

import com.zoer.bepro.model.dao.AbstractJDBCDao;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.Profile;
import com.zoer.bepro.model.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zoer on 27.12.16.
 */
public class MySqlUserDao extends AbstractJDBCDao<User, Integer> {

    @Override
    public User create() throws PersistException {
        return persist(new User());
    }

    @Override
    public String getSelectAllQuery() {
        return "call selectallusers();";
    }

    @Override
    public String getSelectQuery() {
        return "call selectuser(?);";
    }

    @Override
    public String getCreateQuery() {
        return "call insertuser(?,?,?);";
    }

    @Override
    public String getUpdateQuery() {
        return "call updateuser(?,?,?,?);";
    }

    @Override
    public String getDeleteQuery() {
        return "call deleteuser(?)";

    }

    @Override
    public String getSelectLastInsertQuery() {
        return "call selectlastuserinputid();";
    }

    @Override
    protected List<User> parseResultSet(ResultSet rs) throws PersistException {
        List<User> result = new LinkedList<User>();
        try {
            while (rs.next()) {
                PersistsUser user = new PersistsUser();
                user.setId(rs.getInt("iduser"));
                user.setEmail(rs.getString("email"));
                user.setNickname(rs.getString("nickname"));
                user.setPassword(rs.getString("entrypassword"));
                user.setProfileId(user.getId());
                user.setProfile((Profile) MySqlDaoFactory.getInstance().getDao(Profile.class).getByPK(user.getProfileId()));
                result.add(user);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User object) throws PersistException {
        try {
            statement.setString(1, object.getNickname());
            statement.setString(2, object.getEmail());
            statement.setString(3, object.getPassword());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User object) throws PersistException {
        try {
            statement.setInt(1, object.getId());
            statement.setString(2, object.getNickname());
            statement.setString(3, object.getEmail());
            statement.setString(4, object.getPassword());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
    public User getByNickOrEmail(String s) throws PersistException {

        return getBy("call selectuserbynickoremail(?)",s);
    }

    private class PersistsUser extends User {
        public void setId(int id) {
            super.setId(id);

        }
    }

}

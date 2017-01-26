package com.zoer.bepro.model.dao.mysqldao;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.zoer.bepro.model.dao.AbstractJDBCDao;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.Courses;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zoer on 31.12.16.
 */
public class MySqlCoursesDao extends AbstractJDBCDao<Courses, Integer> {

    @Override
    public Courses create() throws PersistException {
        return persist(new Courses());
    }

    @Override
    public String getSelectAllQuery() {
        return "call selectallcourse();";
    }

    @Override
    public String getSelectQuery() {
        return "call selectcourse(?);";
    }

    @Override
    public String getCreateQuery() {
        return "call insertcourse(?,?,?);";
    }

    @Override
    public String getUpdateQuery() {
        return "call updatecourse(?,?,?,?);";
    }

    @Override
    public String getDeleteQuery() {
        return "call deletecourse(?);";
    }

    @Override
    public String getSelectLastInsertQuery() {
        return "call selectlastinsertcourse();";
    }

    @Override
    protected List<Courses> parseResultSet(ResultSet rs) throws PersistException {
        List<Courses> result = new ArrayList<>();
        try {
            while (rs.next()) {
                Courses c = new Courses();
                c.setId(rs.getInt("idcourses"));
                c.setUrl(rs.getString("url"));
                c.setSpecName(rs.getString("specname"));
                c.setSpecId(rs.getInt("specifications_idspecifications"));
                result.add(c);
            }
        } catch (Exception ex) {
            throw new PersistException(ex);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Courses object) throws PersistException {
        try {
            statement.setString(1, object.getUrl());
            statement.setString(2, object.getSpecName());
            statement.setInt(3, object.getSpecId()!=null?object.getSpecId():1);
        } catch (Exception e) {
            throw new PersistException(e);
        }

    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Courses object) throws PersistException {
        try {
            statement.setInt(1, object.getId());
            statement.setString(2, object.getUrl());
            statement.setString(3, object.getSpecName());
            statement.setInt(4, object.getSpecId());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    public List<Courses> getAllBySpec(Integer specId) {
        String sql="select *from courses where specifications_idspecifications=?";
        try(Connection connection = MyDataSourceFactory.getMySQLDataSource().getConnection()) {
            try(PreparedStatement statement=connection.prepareStatement(sql)){
                statement.setInt(1,specId);
                ResultSet rs=statement.executeQuery();
                return parseResultSet(rs);
            } catch (PersistException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

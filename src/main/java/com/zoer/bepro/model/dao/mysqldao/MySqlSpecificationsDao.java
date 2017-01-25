package com.zoer.bepro.model.dao.mysqldao;

import com.zoer.bepro.model.dao.AbstractJDBCDao;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.Specifications;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zoer on 31.12.16.
 */
public class MySqlSpecificationsDao extends AbstractJDBCDao<Specifications,Integer>{

    @Override
    public Specifications create() throws PersistException {
        return persist(new Specifications());
    }

    @Override
    public String getSelectAllQuery() {
        return "call selectallspecification();";
    }

    @Override
    public String getSelectQuery() {
        return "call selectspecification(?);";
    }

    @Override
    public String getCreateQuery() {
        return "call insertspecification(?);";
    }

    @Override
    public String getUpdateQuery() {
        return "call updatespecification(?,?)";
    }

    @Override
    public String getDeleteQuery() {
        return "call deletespecification(?);";
    }

    @Override
    public String getSelectLastInsertQuery() {
        return "call selectlastinsertspecification();";
    }

    public String getSelectAllStudentsSpecifications(){return "call selectAllStudentsSpecifications(?);";}
    public String getSelectAllJobOffersSpecifications(){return "call selectAllJobOffersSpecifications(?);";}

    @Override
    protected List<Specifications> parseResultSet(ResultSet rs) throws PersistException {
        List<Specifications> result = new LinkedList<Specifications>();
        try {
            while (rs.next()) {
                 Specifications specification = new Specifications();
               specification.setId(rs.getInt("idspecifications"));
               specification.setValue(rs.getString("namevalue"));
               result.add(specification);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;

    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Specifications object) throws PersistException {
    try {
    statement.setString(1,object.getValue());
    }catch (Exception e){
        throw new PersistException(e);
    }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Specifications object) throws PersistException {
        try {
            statement.setInt(1,object.getId());
            statement.setString(2,object.getValue());
        }catch (Exception e){
            throw new PersistException();
        }
    }
    public List<Specifications> getAllStudentSpecifications(Integer id) throws PersistException {
        return getAll(getSelectAllStudentsSpecifications().replace("?",id.toString()));
    }
    public List<Specifications> getAllJobOfferSpecifications(Integer id) throws PersistException {
        return getAll(getSelectAllJobOffersSpecifications().replace("?",id.toString()));
    }

}

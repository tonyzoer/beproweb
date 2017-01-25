package com.zoer.bepro.model.dao.mysqldao;

import com.zoer.bepro.model.dao.AbstractJDBCDao;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.CompanyProfile;
import com.zoer.bepro.model.domain.JobOffers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zoer on 31.12.16.
 */
public class MySqlCompanyProfileDao extends AbstractJDBCDao<CompanyProfile,Integer> {

    @Override
    public CompanyProfile create() throws PersistException {
        return persist(new CompanyProfile());
    }

    @Override
    public String getSelectAllQuery() {
        return "call selectallcompanyprofile();";
    }

    @Override
    public String getSelectQuery() {
        return "call selectcompanyprofile(?);";
    }

    @Override
    public String getCreateQuery() {
        return "call insertcompanyprofile(?,?);";
    }

    @Override
    public String getUpdateQuery() {
        return "call updatecompanyprofile(?,?,?);";
    }

    @Override
    public String getDeleteQuery() {
        return "call deletecmpanyprofile(?);";
    }

    @Override
    public String getSelectLastInsertQuery() {
        return "call selectlastinsertcompanyprofile();";
    }

    @Override
    protected List<CompanyProfile> parseResultSet(ResultSet rs) throws PersistException {
        List<CompanyProfile> result=new LinkedList<>();
        try {
            while (rs.next()){
        CompanyProfile cp=new CompanyProfile();
        cp.setIdcompanyprofile(rs.getInt("idcompanyprofile"));
        cp.setInfotxt(rs.getString("infotxt"));
        cp.setImgurl(rs.getString("imgurl"));
        MySqlJobOffersDao jobdao=(MySqlJobOffersDao)MySqlDaoFactory.getInstance().getDao(JobOffers.class);
        cp.setJoboffers(jobdao.compannyjobOffers(cp.getId()));
        result.add(cp);
            }
        }catch (Exception e){
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, CompanyProfile object) throws PersistException {
        try {
            statement.setString(1, object.getInfotxt());
            statement.setString(2, object.getImgurl());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, CompanyProfile object) throws PersistException {
        try {
            statement.setInt(1, object.getId());
            statement.setString(2, object.getInfotxt());
            statement.setString(3, object.getImgurl());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }
    class PersistedCompanyProfile extends CompanyProfile{
        public void setId(int id) {
            super.setIdcompanyprofile(id);

        }
    }
}

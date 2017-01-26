package com.zoer.bepro.model.dao.mysqldao;

import com.zoer.bepro.model.dao.AbstractJDBCDao;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.CompanyProfile;
import com.zoer.bepro.model.domain.JobOffers;
import com.zoer.bepro.model.domain.Specifications;
import com.zoer.bepro.model.domain.StudentProfile;
import javafx.util.Pair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zoer on 31.12.16.
 */
public class MySqlJobOffersDao extends AbstractJDBCDao<JobOffers, Integer> {

    @Override
    public JobOffers create() throws PersistException {
        return persist(new JobOffers());
    }

    @Override
    public String getSelectAllQuery() {
        return "call selectalljoboffer();";
    }

    @Override
    public String getSelectQuery() {
        return "call selectjoboffer(?);";
    }

    @Override
    public String getCreateQuery() {
        return "call insertjoboffer(?,?);";
    }

    @Override
    public String getUpdateQuery() {
        return "call updatejoboffer(?,?,?);";
    }

    @Override
    public String getDeleteQuery() {
        return "call deletejoboffer(?);";
    }

    @Override
    public String getSelectLastInsertQuery() {
        return "call selectlastinsertjoboffer();";
    }

    public String getNewJobOfferSpecificationInsert(){return "call newjobofferspecification(?,?);";}

    @Override
    protected List<JobOffers> parseResultSet(ResultSet rs) throws PersistException {
        List<JobOffers> result = new LinkedList<>();
        try {
            while (rs.next()) {
                JobOffers jo = new JobOffers();
                jo.setId(rs.getInt("idjoboffers"));
                jo.setCompanyId(rs.getInt("companyprofile_idcompanyprofile"));
                jo.setDescription(rs.getString("jobofferstext"));
                result.add(jo);
            }
        } catch (Exception ex) {
            throw new PersistException();
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, JobOffers object) throws PersistException {
        try {

            statement.setInt(1, object.getCompanyId()!=null?object.getCompanyId():1);
            statement.setString(2, object.getDescription());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, JobOffers object) throws PersistException {
        try {

            statement.setInt(1, object.getId());
            statement.setInt(2, object.getCompanyId());
            statement.setString(3, object.getDescription());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    public List<JobOffers> compannyjobOffers(int id) {
        String sql = "Select * from joboffers where companyprofile_idcompanyprofile=" + id + ";";
        List<JobOffers> result = null;
        try {
            result = getAll(sql);
        } catch (PersistException e) {
            e.printStackTrace();//TODO remove this
        }
        return result;
    }
    public List<JobOffers> getCompanyJobOffers(CompanyProfile cp){
        try {
            return getAll("select * from joboffers where companyprofile_idcompanyprofile="+cp.getId()+";");
        } catch (PersistException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<JobOffers> getStudentJobOffers(StudentProfile sp){
        try {
            return getAll("call selectallstudentsjoboffers("+sp.getId()+");");
        } catch (PersistException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Pair<JobOffers,String >> jobOffersWithCompanyNames() throws PersistException {
        String sql="call jobofferswithCompannyNames();";
        List<Pair<JobOffers,String >> result = new LinkedList<>();
        try (Connection connection=MyDataSourceFactory.getMySQLDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                ResultSet rs = statement.executeQuery();
                try {
                    while (rs.next()) {
                        JobOffers jo = new JobOffers();
                        jo.setId(rs.getInt("idjoboffers"));
                        jo.setCompanyId(rs.getInt("companyprofile_idcompanyprofile"));
                        jo.setDescription(rs.getString("jobofferstext"));
                        result.add(new Pair<JobOffers,String >(jo,rs.getString("infotxt")));
                    }
                } catch (Exception ex) {
                    throw new PersistException();
                }
            } catch (Exception e) {
                throw new PersistException(e);
            }
        }catch (SQLException e) {
            throw new PersistException(e);
        }
        return result;
    }
    public void addNewSpecification(JobOffers jo, Specifications spec) throws PersistException {
        try {
            addManyToManyRowToObj(getNewJobOfferSpecificationInsert(),jo,spec);
        } catch (PersistException e) {
            throw new PersistException(e);
        }
    }
    public boolean existsSTudentJobOffer(StudentProfile sp, JobOffers jo){
    try (Connection connection=MyDataSourceFactory.getMySQLDataSource().getConnection()){
    try (PreparedStatement statement=connection.prepareStatement("SELECT * FROM bepro.studentprofile_has_joboffers where studentprofile_idstudentprofile=? and joboffers_idjoboffers=?;")){
    statement.setInt(1,sp.getId());
    statement.setInt(2,jo.getId());
    ResultSet rs=statement.executeQuery();

            if ((rs.next()))
            return true;
    }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
    }
}

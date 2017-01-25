package com.zoer.bepro.model.dao.mysqldao;

import com.zoer.bepro.model.dao.AbstractJDBCDao;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.JobOffers;
import com.zoer.bepro.model.domain.Specifications;
import com.zoer.bepro.model.domain.StudentProfile;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zoer on 31.12.16.
 */
public class MySqlStudentProfileDao extends AbstractJDBCDao<StudentProfile, Integer> {

    class PersistStudentProfile extends StudentProfile {
        public void setId(int id) {
            super.setId(id);

        }
    }


    @Override
    public StudentProfile create() throws PersistException {
        return persist(new StudentProfile());
    }

    @Override
    public String getSelectAllQuery() {
        return "call selectallstudentprofile();";
    }

    @Override
    public String getSelectQuery() {
        return "call selectstudentprofile(?);";
    }

    @Override
    public String getCreateQuery() {
        return "call insertstudentprofile(?,?,?,?);";
    }

    @Override
    public String getUpdateQuery() {
        return "call updatestudentprofile(?,?,?,?,?);";
    }

    @Override
    public String getDeleteQuery() {
        return "call deletestudentprofile(?);";
    }

    @Override
    public String getSelectLastInsertQuery() {
        return "call selectlastinsertstudentprofile();";
    }

    public String getNewStudentSpecificationInsertQuery() {
        return "call newstudentsspecification(?,?)";
    }

    public String getNewStudentJobOfferInsertQuery() {
        return "call newstudentsjoboffer(?,?)";
    }

    @Override
    protected List<StudentProfile> parseResultSet(ResultSet rs) throws PersistException {
        List<StudentProfile> result = new LinkedList<StudentProfile>();
        try {
            while (rs.next()) {
                PersistStudentProfile studentProfile = new PersistStudentProfile();
                studentProfile.setId(rs.getInt("idstudentprofile"));
                studentProfile.setName(rs.getString("username"));
                studentProfile.setCountry(rs.getString("country"));
                studentProfile.setTel(rs.getString("tel"));
                studentProfile.setCvurl(rs.getString("cvurl"));
//                MySqlSpecificationsDao dao= (MySqlSpecificationsDao) MySqlDaoFactory.getInstance().getDao(Specifications.class);
//                studentProfile.setSpecifications( dao.getAllStudentSpecifications(studentProfile.getId()));
                result.add(studentProfile);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, StudentProfile object) throws PersistException {
        try {
            statement.setString(1, object.getName() != null ? object.getName() : "NoNameTestString");
            statement.setString(2, object.getCountry());
            statement.setString(3, object.getTel());
            statement.setString(4, object.getCvurl());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, StudentProfile object) throws PersistException {
        try {
            statement.setInt(1, object.getId());
            statement.setString(2, object.getName() != null ? object.getName() : "NoNameTestString");
            statement.setString(3, object.getCountry());
            statement.setString(4, object.getTel());
            statement.setString(5, object.getCvurl());
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    public void addNewSpecification(StudentProfile studentProfile, Specifications spec) throws PersistException {
        try {
            addManyToManyRowToObj(getNewStudentSpecificationInsertQuery(), studentProfile, spec);
        } catch (PersistException e) {
            throw new PersistException(e);
        }
    }

    public void addNewJobOffer(StudentProfile studentProfile, JobOffers jo) throws PersistException {
        try {
            addManyToManyRowToObj(getNewStudentSpecificationInsertQuery(), studentProfile, jo);
        } catch (PersistException e) {
            throw new PersistException(e);
        }
    }
}

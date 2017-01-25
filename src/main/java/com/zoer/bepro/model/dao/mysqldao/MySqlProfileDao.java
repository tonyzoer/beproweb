package com.zoer.bepro.model.dao.mysqldao;

import com.zoer.bepro.model.dao.AbstractJDBCDao;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.Profile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zoer on 29.12.16.
 */
public class MySqlProfileDao extends AbstractJDBCDao<Profile, Integer> {


    @Override
    public Profile create() throws PersistException {
        return persist(new Profile());
    }

    @Override
    public String getSelectAllQuery() {
        return "call selectallprofiles();";
    }

    @Override
    public String getSelectQuery() {
        return "call selectprofile(?);";
    }

    @Override
    public String getCreateQuery() {
        return "call insertprofile(?,?,?,?);";
    }

    @Override
    public String getUpdateQuery() {
        return "call updateprofile(?,?,?,?);";
    }

    @Override
    public String getDeleteQuery() {
        return "call deleteprofile(?);";
    }

    @Override
    public String getSelectLastInsertQuery() {
        return "call selectlastinsertprofile();";
    }

    @Override
    protected List<Profile> parseResultSet(ResultSet rs) throws PersistException {
        List<Profile> result = new LinkedList<Profile>();
        try {
            while (rs.next()) {
                PersistsProfile profile = new PersistsProfile();
                profile.setId(rs.getInt("idprofile"));
                int stud = rs.getInt("studentprofile_idstudentprofile");
                if (stud != 0) {
                    //TODO after creating every dao of profiles - uncoment this
                    MySqlStudentProfileDao studentProfileDao = new MySqlStudentProfileDao();
                    profile.setStudentProfile(studentProfileDao.getByPK(stud));
                }
//                else {
//                   profile.setStudentProfile(null);//можна убрать (для читаемости кода)
//                }
                int com = rs.getInt("companyprofile_idcompanyprofile");
                if (com != 0) {
                    MySqlCompanyProfileDao mySqlCompanyProfileDao = new MySqlCompanyProfileDao();
                    profile.setCompanyProfile(mySqlCompanyProfileDao.getByPK(com));
                }
//                else {
////                    profile.setCompanyProfile(null);//можна убрать (для читаемости кода)
//                }
                int adm = rs.getInt("adminprofile_idadminprofile");
                if (adm != 0) {
//                    MySqlAdminProfile adminProfile = new MySqlAdminProfile(connection);
//                    profile.setAdminProfile(adminProfile.getByPK(adm));
                }
//                else {
//                    profile.setAdminProfile(null);//можна убрать (для читаемости кода)
//                }
                result.add(profile);
            }
        } catch (Exception e) {
            throw new PersistException(e);
        }
        return result;

    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Profile object) throws PersistException {
        try {
            statement.setInt(1,object.getId()!=null?object.getId():0);
            statement.setInt(2, (object.getStudentProfile().isPresent()) ? object.getStudentProfile().get().getId() : 0);
            statement.setInt(3, (object.getCompanyProfile().isPresent()) ? object.getCompanyProfile().get().getId() : 0);
            statement.setInt(4, (object.getAdminProfile().isPresent()) ? object.getAdminProfile().get().getId() : 0);
        } catch (Exception e) {
            throw new PersistException(e);
        }
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Profile object) throws PersistException {
        try {
            statement.setInt(1, object.getId());
            statement.setInt(2, (object.getStudentProfile().isPresent()) ? object.getStudentProfile().get().getId() : 0);
            statement.setInt(3, (object.getCompanyProfile().isPresent()) ? object.getCompanyProfile().get().getId() : 0);
            statement.setInt(4, (object.getAdminProfile().isPresent()) ? object.getAdminProfile().get().getId() : 0);
        } catch (Exception e) {
            throw new PersistException(e);
        }

    }

    private class PersistsProfile extends Profile {
        public void setId(int id) {
            super.setId(id);

        }
    }
}

package com.zoer.bepro.model.services.impl;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.zoer.bepro.model.dao.AbstractJDBCDao;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.dao.mysqldao.MySqlDaoFactory;
import com.zoer.bepro.model.dao.mysqldao.MySqlStudentProfileDao;
import com.zoer.bepro.model.domain.JobOffers;
import com.zoer.bepro.model.domain.Specifications;
import com.zoer.bepro.model.domain.StudentProfile;
import com.zoer.bepro.model.services.ServiceFactory;
import com.zoer.bepro.model.services.StudentProfileService;


/**
 * Created by Zoer on 13.01.2017.
 */
public class DefaultStudentProfileService extends GenericEntityService<StudentProfile> implements StudentProfileService {
    public static DefaultStudentProfileService getInstance() {
        return instance;
    }

    static private DefaultStudentProfileService instance =new DefaultStudentProfileService();

    @Override
    AbstractJDBCDao<StudentProfile, Integer> getDao() throws PersistException {
        return (MySqlStudentProfileDao) MySqlDaoFactory.getInstance().getDao(StudentProfile.class);
    }


    @Override
    public boolean addSpecification(Specifications spec, StudentProfile sp)  {
        try {
            addSpecificationImp(spec,sp);
        } catch (PersistException e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean addSpecification(String spec, StudentProfile sp) {
        Specifications newSpec= null;
        try {
            newSpec = DefaultSpecificationService.getInstance().insert(new Specifications(){{setValue(spec);}});
            addSpecificationImp(newSpec,sp);
        } catch (PersistException e) {
            return false;
        }
        return true;
    }
    private void addSpecificationImp(Specifications spec,StudentProfile sp) throws PersistException {
        MySqlStudentProfileDao dao=(MySqlStudentProfileDao)getDao();
            dao.addNewSpecification(sp,spec);
    }
    public boolean addJobOffer(StudentProfile sp,JobOffers jo) {
        try {
            MySqlStudentProfileDao dao=(MySqlStudentProfileDao)getDao();
            dao.addNewJobOffer(sp,jo);
        } catch (PersistException e) {
            return false;
        }
        return true;
    }
}

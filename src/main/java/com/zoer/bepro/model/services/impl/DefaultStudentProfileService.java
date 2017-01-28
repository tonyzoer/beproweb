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
import org.apache.log4j.Logger;

import java.util.Iterator;
import java.util.List;


/**
 * Created by Zoer on 13.01.2017.
 */
public class DefaultStudentProfileService extends GenericEntityService<StudentProfile> implements StudentProfileService {
    DefaultStudentProfileService() {
    }

    private final static Logger logger = Logger.getLogger(DefaultStudentProfileService.class);
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
            newSpec = DefaultServiceFactory.getInstance().getDefaultSpecificationService().insert(new Specifications(){{setValue(spec);}});
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
    public List<StudentProfile> getStudentsByJobOffer(JobOffers jo){
        try {
            MySqlStudentProfileDao dao=(MySqlStudentProfileDao)getDao();
            return dao.allStudentsByJobOffer(jo.getId());
        } catch (PersistException e) {
         logger.debug(e);
        }
        return null;
    }
    public List<StudentProfile>getStudentToComfirmJobOffer(JobOffers jo){
        List<StudentProfile> students= getStudentsByJobOffer(jo);
        List<Specifications> specToKnow= DefaultServiceFactory.getInstance().getDefaultSpecificationService().getJobOfferSpecifications(jo.getId());
        Iterator<StudentProfile> it=students.iterator();
        while (it.hasNext()) {
            if(!DefaultServiceFactory.getInstance().getDefaultSpecificationService().getStudentsSpecifications(it.next().getId()).containsAll(specToKnow)){
                it.remove();
            }
        }
        return students;
    }
}

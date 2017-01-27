package com.zoer.bepro.model.services.impl;

import com.zoer.bepro.model.dao.AbstractJDBCDao;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.dao.mysqldao.MySqlDaoFactory;
import com.zoer.bepro.model.dao.mysqldao.MySqlJobOffersDao;
import com.zoer.bepro.model.domain.*;
import com.zoer.bepro.model.services.JobOffersService;
import javafx.util.Pair;

import java.util.List;

/**
 * Created by Zoer on 13.01.2017.
 */
public class DefaultJobOffersService extends GenericEntityService<JobOffers> implements JobOffersService{
    private static DefaultJobOffersService instance=new DefaultJobOffersService();

    public static DefaultJobOffersService getInstance() {
        return instance;
    }
    @Override
    AbstractJDBCDao<JobOffers, Integer> getDao() throws PersistException {
        return (AbstractJDBCDao<JobOffers, Integer>) MySqlDaoFactory.getInstance().getDao(JobOffers.class);
    }
    @Override
   public List<Pair<JobOffers,String >> getJobOffersWithCompanyNames(){
       try {
           MySqlJobOffersDao dao= (MySqlJobOffersDao) getDao();
           return dao.jobOffersWithCompanyNames();
       } catch (PersistException e) {
           e.printStackTrace();
       }
       return null;
   }
   @Override
   public void addSpecificationToJobOffer(JobOffers jo, Specifications spec){
       try {
           MySqlJobOffersDao dao= (MySqlJobOffersDao) getDao();
           dao.addNewSpecification(jo,spec);
       } catch (PersistException e) {
           e.printStackTrace();
       }
   }
    public List<JobOffers> getCompanyJobOffers(CompanyProfile cp){
        try {
            MySqlJobOffersDao dao= (MySqlJobOffersDao) getDao();
            return dao.getCompanyJobOffers(cp);
        } catch (PersistException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<JobOffers> getStudentsJobOffers(StudentProfile sp){
        try {
            MySqlJobOffersDao dao= (MySqlJobOffersDao) getDao();
            return dao.getStudentJobOffers(sp);
        } catch (PersistException e) {
            e.printStackTrace();
        }
        return null;
    }
    public boolean existsSTudentJobOffer(StudentProfile sp,JobOffers jo){
        try {
            MySqlJobOffersDao dao= (MySqlJobOffersDao) getDao();
            return dao.existsStudentJobOffer(sp,jo);
        } catch (PersistException e) {
            e.printStackTrace();
        }
        return false;
    }

}

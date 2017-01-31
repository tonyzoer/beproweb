package com.zoer.bepro.model.services.impl;

import com.zoer.bepro.model.dao.AbstractJDBCDao;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.dao.mysqldao.MySqlDaoFactory;
import com.zoer.bepro.model.dao.mysqldao.MySqlSpecificationsDao;
import com.zoer.bepro.model.domain.Courses;
import com.zoer.bepro.model.domain.Specifications;
import com.zoer.bepro.model.services.SpecificationsService;
import com.zoer.bepro.utils.MyCourseraApi;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zoer on 20.01.17.
 */
public class DefaultSpecificationService extends GenericEntityService<Specifications> implements SpecificationsService {
    DefaultSpecificationService() {
    }

    private final static Logger logger = Logger.getLogger(DefaultSpecificationService.class);

    @Override
    AbstractJDBCDao<Specifications, Integer> getDao() throws PersistException {
        return (MySqlSpecificationsDao) MySqlDaoFactory.getInstance().getDao(Specifications.class);
    }

    @Override
    public List<Specifications> getStudentsSpecifications(Integer studentId) {
        List<Specifications> specifications = new ArrayList<>();
        try {
            MySqlSpecificationsDao dao = (MySqlSpecificationsDao) getDao();
            specifications = dao.getAllStudentSpecifications(studentId);
        } catch (PersistException e) {
            e.printStackTrace();
        }
        return specifications;
    }

    @Override
    public List<Specifications> getJobOfferSpecifications(Integer jobOfferId) {
        List<Specifications> specifications = new ArrayList<>();
        try {
            MySqlSpecificationsDao dao = (MySqlSpecificationsDao) getDao();
            specifications = dao.getAllJobOfferSpecifications(jobOfferId);
        } catch (PersistException e) {
            e.printStackTrace();
        }
        return specifications;
    }

    @Override
    public Specifications insert(Specifications spec) {
        Specifications specc = null;
        try {
            specc = super.insert(spec);
            specc.setCoursesList(MyCourseraApi.firstNCoursesByName(spec.getValue()));
            for (Courses cou : specc.getCoursesList()) {
                cou.setSpecId(specc.getId());
                DefaultServiceFactory.getInstance().getDefaultCoursesService().insert(cou);

            }
        } catch (PersistException e) {
            logger.debug(e);
        }
        return specc;
    }
}


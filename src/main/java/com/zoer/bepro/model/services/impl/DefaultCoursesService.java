package com.zoer.bepro.model.services.impl;

import com.zoer.bepro.model.dao.AbstractJDBCDao;
import com.zoer.bepro.model.dao.DaoFactory;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.dao.mysqldao.MySqlCoursesDao;
import com.zoer.bepro.model.dao.mysqldao.MySqlDaoFactory;
import com.zoer.bepro.model.domain.Courses;
import com.zoer.bepro.model.domain.Specifications;
import com.zoer.bepro.model.services.ServiceFactory;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by Zoer on 13.01.2017.
 */
public class DefaultCoursesService extends GenericEntityService<Courses> {

    DefaultCoursesService() {
    }

    private final static Logger logger = Logger.getLogger(DefaultCoursesService.class);
    @Override
    AbstractJDBCDao<Courses, Integer> getDao() throws PersistException {
        return (AbstractJDBCDao<Courses, Integer>) MySqlDaoFactory.getInstance().getDao(Courses.class);
    }


    public List<Courses> getAllSpecCourses(Integer specId) {
        try {
            MySqlCoursesDao dao = (MySqlCoursesDao) getDao();
            return dao.getAllBySpec(specId);
        } catch (PersistException e) {
            logger.debug(e);
        }
        return null;
    }
}

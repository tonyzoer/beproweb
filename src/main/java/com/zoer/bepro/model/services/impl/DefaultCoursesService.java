package com.zoer.bepro.model.services.impl;

import com.zoer.bepro.model.dao.AbstractJDBCDao;
import com.zoer.bepro.model.dao.DaoFactory;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.dao.mysqldao.MySqlDaoFactory;
import com.zoer.bepro.model.domain.Courses;
import com.zoer.bepro.model.services.ServiceFactory;

/**
 * Created by Zoer on 13.01.2017.
 */
public class DefaultCoursesService extends  GenericEntityService<Courses> {
        private static DefaultCoursesService instance=new DefaultCoursesService();

    public static DefaultCoursesService getInstance() {
        return instance;
    }

    @Override
    AbstractJDBCDao<Courses, Integer> getDao() throws PersistException {
        return (AbstractJDBCDao<Courses, Integer>) MySqlDaoFactory.getInstance().getDao(Courses.class);
    }


}

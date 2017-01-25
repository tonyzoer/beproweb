package com.zoer.bepro.model.services.impl;

import com.zoer.bepro.model.dao.AbstractJDBCDao;
import com.zoer.bepro.model.domain.Courses;
import com.zoer.bepro.model.services.ServiceFactory;

/**
 * Created by Zoer on 13.01.2017.
 */
public class DefaultCoursesService extends  GenericEntityService<Courses> {
    @Override
    AbstractJDBCDao<Courses, Integer> getDao() {
        return null;
    }


}

package com.zoer.bepro.model.services.impl;

import com.zoer.bepro.model.dao.AbstractJDBCDao;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.dao.mysqldao.MySqlCompanyProfileDao;
import com.zoer.bepro.model.dao.mysqldao.MySqlDaoFactory;
import com.zoer.bepro.model.domain.CompanyProfile;

/**
 * Created by Zoer on 13.01.2017.
 */
public class DefaultCompanyProfileService extends GenericEntityService<CompanyProfile> {


    DefaultCompanyProfileService() {
    }

    @Override
    AbstractJDBCDao<CompanyProfile, Integer> getDao() throws PersistException {
        return (MySqlCompanyProfileDao) MySqlDaoFactory.getInstance().getDao(CompanyProfile.class);
    }


}

package com.zoer.bepro.model.services.impl;

import com.zoer.bepro.model.dao.AbstractJDBCDao;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.dao.mysqldao.MySqlCompanyProfileDao;
import com.zoer.bepro.model.dao.mysqldao.MySqlDaoFactory;
import com.zoer.bepro.model.domain.CompanyProfile;
import com.zoer.bepro.model.services.ServiceFactory;

/**
 * Created by Zoer on 13.01.2017.
 */
public class DefaultCompanyProfileService extends GenericEntityService<CompanyProfile> {
    static private DefaultCompanyProfileService instance=new DefaultCompanyProfileService();

    static public DefaultCompanyProfileService getInstance() {
        return instance;
    }

    @Override
    AbstractJDBCDao<CompanyProfile, Integer> getDao() throws PersistException {
        return (MySqlCompanyProfileDao) MySqlDaoFactory.getInstance().getDao(CompanyProfile.class);
    }


}

package com.zoer.bepro.model.services.impl;

import com.zoer.bepro.model.dao.AbstractJDBCDao;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.AdminProfile;
import com.zoer.bepro.model.domain.CompanyProfile;
import com.zoer.bepro.model.services.AdminProfileService;

/**
 * Created by zoer on 18.01.17.
 */
public class DefaultAdminProfileService extends GenericEntityService<AdminProfile> implements AdminProfileService {
    public static DefaultAdminProfileService getInstance() {
        return instance;
    }

    static private DefaultAdminProfileService instance=new DefaultAdminProfileService();

    @Override
    AbstractJDBCDao<AdminProfile, Integer> getDao() throws PersistException {
        return null;
    }
}

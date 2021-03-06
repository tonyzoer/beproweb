package com.zoer.bepro.model.services.impl;

import com.zoer.bepro.model.dao.AbstractJDBCDao;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.dao.mysqldao.MySqlDaoFactory;
import com.zoer.bepro.model.dao.mysqldao.MySqlProfileDao;
import com.zoer.bepro.model.domain.*;
import com.zoer.bepro.model.services.ProfileServices;
import com.zoer.bepro.model.services.ProfileType;
import org.apache.log4j.Logger;

/**
 * Created by Zoer on 13.01.2017.
 */
public class DefaultProfileService extends GenericEntityService<Profile> implements ProfileServices {
    private static final  Logger logger = Logger.getLogger(DefaultProfileService.class);

    DefaultProfileService() {
    }
    @Override
    AbstractJDBCDao<Profile, Integer> getDao() throws PersistException {
        return (MySqlProfileDao) MySqlDaoFactory.getInstance().getDao(Profile.class);
    }


    @Override
    public ProfileType getProfileType(Profile prfl) {

        return prfl.getStudentProfile().isPresent() ? ProfileType.STUDENT : prfl.getCompanyProfile().isPresent() ? ProfileType.COMPANY : prfl.getAdminProfile().isPresent() ? ProfileType.ADMIN : ProfileType.NOONE;
    }

    @Override
    public Profile getProfile(User user) {
        try {
            return getDao().getByPK(user.getId());
        } catch (PersistException e) {
            logger.debug(e);
        }
        return null;
    }

    @Override
    public void createStudentProfile(Profile prfl, StudentProfile studentProfile) throws PersistException {
        StudentProfile studentProfilet = DefaultServiceFactory.getInstance().getDefaultStudentProfileService().insert(studentProfile);
        prfl.setStudentProfile(studentProfilet);
        update(prfl);
    }

    @Override
    public void createCompanyProfile(Profile prfl, CompanyProfile companyProfile) throws PersistException {
        CompanyProfile companyProfilet = DefaultServiceFactory.getInstance().getDefaultCompanyProfileService().insert(companyProfile);
        prfl.setCompanyProfile(companyProfilet);
        update(prfl);
    }

    @Override
    public void createAdminProfile(Profile prfl, AdminProfile adminProfile) throws PersistException {
        AdminProfile adminProfilet=DefaultAdminProfileService.getInstance().insert(adminProfile);
        prfl.setAdminProfile(adminProfilet);
        update(prfl);
    }
}

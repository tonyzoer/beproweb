package com.zoer.bepro.model.services.impl;

import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.dao.mysqldao.MySqlDaoFactory;
import com.zoer.bepro.model.dao.mysqldao.MySqlUserDao;
import com.zoer.bepro.model.domain.CompanyProfile;
import com.zoer.bepro.model.domain.StudentProfile;
import com.zoer.bepro.model.domain.User;
import com.zoer.bepro.model.services.UserService;
import org.apache.log4j.Logger;


public class DefaultUserSevice extends GenericEntityService<User> implements UserService {
    private static final Logger logger = Logger.getLogger(DefaultUserSevice.class);

    DefaultUserSevice() {
    }

    @Override
    MySqlUserDao getDao() throws PersistException {
        return (MySqlUserDao) MySqlDaoFactory.getInstance().getDao(User.class);
    }


    @Override
    public User authentication(String loginOrMail, String password) {
        User user = null;
        try {
            user = getDao().getByNickOrEmail(loginOrMail);
        } catch (PersistException e) {
            logger.debug(e);
        }
        if (user != null && user.getPassword().equals(password))
            return user;
        return null;
    }

    @Override
    public User getUser(String loginOrMail) {
        try {
            return getDao().getByNickOrEmail(loginOrMail);

        } catch (PersistException e) {
            logger.debug(e);
        }
        return null;
    }

    @Override
    public User getUser(StudentProfile profile) {
        try {
            return getDao().getByProfile(profile);
        } catch (PersistException e) {
            logger.debug(e);
        }
        return null;
    }

    @Override
    public User getUser(CompanyProfile profile) {
        try {
            return getDao().getByProfile(profile);
        } catch (PersistException e) {
            logger.debug(e);
        }
        return null;
    }


}

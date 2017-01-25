package com.zoer.bepro.model.services.impl;

import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.dao.mysqldao.MySqlDaoFactory;
import com.zoer.bepro.model.dao.mysqldao.MySqlUserDao;
import com.zoer.bepro.model.domain.User;
import com.zoer.bepro.model.services.UserService;


public class DefaultUserSevice extends GenericEntityService<User> implements UserService {
    private static final DefaultUserSevice instance = new DefaultUserSevice();

    private DefaultUserSevice() {
    }

    public static DefaultUserSevice getInstance() {
        return instance;
    }

    @Override
    MySqlUserDao getDao() throws PersistException {
        return (MySqlUserDao) MySqlDaoFactory.getInstance().getDao(User.class);
    }



    @Override
    public User authentication(String loginOrMail, String password) throws PersistException {
        User user = getDao().getByNickOrEmail(loginOrMail);
        if (user!=null && user.getPassword().equals(password))
            return user;
        return null;
    }

    @Override
    public User insert(User user) throws PersistException {
        if (getDao().getByNickOrEmail(user.getNickname()) != null)
            throw new PersistException("This accaunt is already exsists");
     return super.insert(user);

    }

    @Override
    public void update(User user) throws PersistException{
        User sqlUser = getDao().getByNickOrEmail(user.getNickname());
        if (sqlUser == null || sqlUser.getId() != user.getId())
            throw new PersistException("This user not exists");
        super.update(user);
    }
}

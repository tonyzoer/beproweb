package com.zoer.bepro.model.services;

import com.zoer.bepro.model.dao.Identified;
import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.CompanyProfile;
import com.zoer.bepro.model.domain.Profile;
import com.zoer.bepro.model.domain.StudentProfile;
import com.zoer.bepro.model.domain.User;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Zoer on 13.01.2017.
 */
public interface UserService extends EntityService<User> {
    User authentication(String login, String password) throws PersistException;

    User getUser(String loginOrMail);

    User getUser(StudentProfile profile);

    User getUser(CompanyProfile profile);
}

package com.zoer.bepro.model.services;

import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.*;

/**
 * Created by Zoer on 13.01.2017.
 */
public interface ProfileServices extends EntityService<Profile> {
ProfileType getProfileType(Profile prfl);
Profile getProfile(User user);
void createStudentProfile(Profile prfl, StudentProfile studentProfile) throws PersistException;
void createCompanyProfile(Profile prfl, CompanyProfile companyProfile) throws PersistException;
void createAdminProfile(Profile prfl, AdminProfile adminProfile) throws PersistException;
}

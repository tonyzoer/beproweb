package com.zoer.bepro.model.services;

import com.zoer.bepro.model.dao.PersistException;
import com.zoer.bepro.model.domain.JobOffers;
import com.zoer.bepro.model.domain.Specifications;
import com.zoer.bepro.model.domain.StudentProfile;

import java.util.List;

/**
 * Created by Zoer on 13.01.2017.
 */
public interface StudentProfileService extends EntityService<StudentProfile> {
public boolean addSpecification(Specifications spec,StudentProfile sp) throws PersistException;
public boolean addSpecification(String spec,StudentProfile sp) throws PersistException;

    boolean addJobOffer(StudentProfile sp, JobOffers jo);

    List<StudentProfile> getStudentsByJobOffer(JobOffers jo);

    List<StudentProfile> getStudentToComfirmJobOffer(JobOffers jo);
}

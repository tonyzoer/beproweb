package com.zoer.bepro.model.services;

import com.zoer.bepro.model.domain.CompanyProfile;
import com.zoer.bepro.model.domain.JobOffers;
import com.zoer.bepro.model.domain.Specifications;
import com.zoer.bepro.model.domain.StudentProfile;
import javafx.util.Pair;

import java.util.List;

/**
 * Created by Zoer on 13.01.2017.
 */
public interface JobOffersService extends EntityService<JobOffers> {
     List<Pair<JobOffers,String >> getJobOffersWithCompanyNames();
     void addSpecificationToJobOffer(JobOffers jo, Specifications spec);

    List<JobOffers> getCompanyJobOffers(CompanyProfile cp);

    List<JobOffers> getStudentsJobOffers(StudentProfile sp);

    boolean existsSTudentJobOffer(StudentProfile sp, JobOffers jo);
}

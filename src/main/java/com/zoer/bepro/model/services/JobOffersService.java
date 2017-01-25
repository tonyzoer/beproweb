package com.zoer.bepro.model.services;

import com.zoer.bepro.model.domain.JobOffers;
import com.zoer.bepro.model.domain.Specifications;
import javafx.util.Pair;

import java.util.List;

/**
 * Created by Zoer on 13.01.2017.
 */
public interface JobOffersService extends EntityService<JobOffers> {
     List<Pair<JobOffers,String >> getJobOffersWithCompanyNames();
     void addSpecificationToJobOffer(JobOffers jo, Specifications spec);
}

package com.zoer.bepro.model.services;

import com.zoer.bepro.model.domain.Specifications;

import java.util.List;

/**
 * Created by Zoer on 13.01.2017.
 */
public interface SpecificationsService extends EntityService<Specifications> {

    List<Specifications> getStudentsSpecifications(Integer studentId);

    List<Specifications> getJobOfferSpecifications(Integer jobOfferId);
}

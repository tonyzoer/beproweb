package com.zoer.bepro.model.domain;

import com.zoer.bepro.model.dao.Identified;

/**
 * Created by zoer on 27.12.16.
 */
public class JobOffers implements Identified<Integer> {
    Integer Id;
    Integer CompanyId;
    String Description;
    
    public Integer getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(Integer companyId) {
        CompanyId = companyId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }
}

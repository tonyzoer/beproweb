package com.zoer.bepro.model.domain;

import com.zoer.bepro.model.dao.Identified;

/**
 * Created by zoer on 27.12.16.
 */
public class JobOffers implements Identified<Integer> {
    Integer id;
    Integer companyId;
    String description;
    
    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Override
    public int compareTo(Identified<Integer> integerIdentified) {
        return this.getId()-integerIdentified.getId();
    }
}

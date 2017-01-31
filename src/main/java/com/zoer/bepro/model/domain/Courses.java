package com.zoer.bepro.model.domain;

import com.zoer.bepro.model.dao.Identified;

/**
 * Created by zoer on 27.12.16.
 */
public class Courses implements Identified<Integer> {
    Integer id;
    Integer specId;
    String url;
    String specName;

    public Integer getSpecId() {
        return specId;
    }

    public void setSpecId(Integer specId) {
        this.specId = specId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSpecName() {
        return specName;
    }

    public void setSpecName(String specName) {
        this.specName = specName;
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

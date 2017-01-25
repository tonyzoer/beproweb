package com.zoer.bepro.model.domain;

import com.zoer.bepro.model.dao.Identified;

import java.util.List;

/**
 * Created by zoer on 27.12.16.
 */
public class StudentProfile implements Identified<Integer> {
    Integer id;
    String name;
    String country;
    String tel;
    String cvurl;
    List<Specifications> specifications;
    

    public List<Specifications> getSpecifications() {
        return specifications;
    }

    public void setSpecifications(List<Specifications> specifications) {
        this.specifications = specifications;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCvurl() {
        return cvurl;
    }

    public void setCvurl(String cvurl) {
        this.cvurl = cvurl;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

package com.zoer.bepro.model.domain;

import com.zoer.bepro.model.dao.Identified;

import java.util.List;

/**
 * Created by zoer on 27.12.16.
 */
public class Specifications implements Identified<Integer> {
    Integer id;
    String value;
    List<Courses> coursesList;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<Courses> getCoursesList() {
        return coursesList;
    }

    public void setCoursesList(List<Courses> coursesList) {
        this.coursesList = coursesList;
    }

    @Override

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Override
    public boolean equals(Object s){
        return  (((Specifications) s).getId().equals(this.getId()));
    }
}

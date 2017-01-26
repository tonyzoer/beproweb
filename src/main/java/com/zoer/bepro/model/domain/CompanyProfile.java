package com.zoer.bepro.model.domain;

import com.zoer.bepro.model.dao.Identified;

import java.util.List;

/**
 * Created by zoer on 27.12.16.
 */
public class CompanyProfile implements Identified<Integer> {


    Integer idcompanyprofile;
    String infotxt;
    String imgurl;
    List<JobOffers> joboffers;



    public List<JobOffers> getJoboffers() {
        return joboffers;
    }

    public void setJoboffers(List<JobOffers> joboffers) {
        this.joboffers = joboffers;
    }

    public void setIdcompanyprofile(Integer idcompanyprofile) {
        this.idcompanyprofile = idcompanyprofile;
    }

    public String getInfotxt() {
        return infotxt;
    }

    public void setInfotxt(String infotxt) {
        this.infotxt = infotxt;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    @Override
    public Integer getId() {
        return idcompanyprofile;
    }

}

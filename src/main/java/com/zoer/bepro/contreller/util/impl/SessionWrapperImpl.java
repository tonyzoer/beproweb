package com.zoer.bepro.contreller.util.impl;


import com.zoer.bepro.contreller.util.SessionWrapper;
import com.zoer.bepro.model.domain.CompanyProfile;
import com.zoer.bepro.model.domain.Specifications;
import com.zoer.bepro.model.domain.StudentProfile;
import com.zoer.bepro.model.domain.User;
import com.zoer.bepro.model.services.ProfileType;

import javax.servlet.http.HttpSession;
import java.util.List;

public class SessionWrapperImpl implements SessionWrapper {
    private HttpSession session;

    public SessionWrapperImpl(HttpSession session) {
        this.session = session;
    }

    @Override
    public void invalidate() {
        if(session!=null)
        session.invalidate();
    }

    @Override
    public void setUser(User user) {
        session.setAttribute("user", user);
    }

    @Override
    public User getUser() {
        return session==null?null:(User) session.getAttribute("user");
    }

    @Override
    public void setLanguage(String lang) {
        session.setAttribute("lang", lang);
    }

    @Override
    public void setProfileType(ProfileType type) {
    session.setAttribute("profileType",type);
    }

    @Override
    public ProfileType getProfileType() {
        return (ProfileType) session.getAttribute("profileType");
    }


    @Override
    public void setSdudentsSpecifications(List<Specifications> lst) {
        session.setAttribute("studentSpecifications",lst);
    }

    @Override
    public List<Specifications> getStudentSpecifications() {
        return (List<Specifications>) session.getAttribute("studentSpecifications");
    }

    @Override
    public void setSpecifications(List<Specifications> lst) {
        session.setAttribute("allspecifications",lst);
    }

    @Override
    public List<Specifications> getSpecifications() {
        return (List<Specifications>) session.getAttribute("allspecifications");
    }

}

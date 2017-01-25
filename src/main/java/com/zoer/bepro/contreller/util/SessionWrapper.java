package com.zoer.bepro.contreller.util;


import com.zoer.bepro.model.domain.CompanyProfile;
import com.zoer.bepro.model.domain.Specifications;
import com.zoer.bepro.model.domain.StudentProfile;
import com.zoer.bepro.model.domain.User;
import com.zoer.bepro.model.services.ProfileType;

import java.util.List;

public interface SessionWrapper {
    void invalidate();
    void setUser(User user);
    User getUser();
    void setLanguage(String lang);
    void setProfileType(ProfileType type);
    ProfileType getProfileType();
    void setStudentProfile(StudentProfile sp);
    StudentProfile getStudentProfile();
    void setCompanyProfile(CompanyProfile cp);
    CompanyProfile getCompanyProfile();
    void setSdudentsSpecifications(List<Specifications> lst);
    List<Specifications> getStudentSpecifications();
    void setSpecifications(List<Specifications> lst);
    List<Specifications> getSpecifications();
}

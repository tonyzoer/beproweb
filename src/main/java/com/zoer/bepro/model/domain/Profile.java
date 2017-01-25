package com.zoer.bepro.model.domain;

import com.zoer.bepro.model.dao.Identified;

import java.util.Optional;

/**
 * Created by zoer on 27.12.16.
 */
public class Profile implements Identified<Integer> {
    Integer id;
    AdminProfile adminProfile;
    CompanyProfile companyProfile;
    StudentProfile studentProfile;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Optional<AdminProfile> getAdminProfile() {
        return Optional.ofNullable(adminProfile);
    }

    public void setAdminProfile(AdminProfile adminProfile) {
        this.adminProfile = adminProfile;
    }

    public Optional<CompanyProfile> getCompanyProfile() {
        return Optional.ofNullable(companyProfile);
    }

    public void setCompanyProfile(CompanyProfile companyProfile) {
        this.companyProfile = companyProfile;
    }

    public Optional<StudentProfile> getStudentProfile() {
        return Optional.ofNullable(studentProfile);
    }

    public void setStudentProfile(StudentProfile studentProfile) {
        this.studentProfile = studentProfile;
    }
}

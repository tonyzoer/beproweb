package com.zoer.bepro.model.services;

import com.zoer.bepro.model.domain.CompanyProfile;
import com.zoer.bepro.model.domain.Courses;
import com.zoer.bepro.model.domain.Specifications;
import com.zoer.bepro.model.domain.StudentProfile;
import com.zoer.bepro.model.services.*;

/**
 * Created by Zoer on 13.01.2017.
 */
public interface ServiceFactory {
    CompanyProfileService getCompanyProfileService();
    CoursesService getCourseService();
    JobOffersService getJobOffersService();
    ProfileServices getProfileServices();
    SpecificationsService getSpecificationsService();
    StudentProfileService getStudentProfileService();
    UserService getUserService();
}
